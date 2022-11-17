<template>
    <div>
        <!-- 小页面标题 -->
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-lx-cascades"></i> 路线信息管理
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <!-- 路线表单 -->
        <div class="container">
            <!-- 标题工具栏 -->
            <div class="handle-box">
                <el-select v-model="query.mode" placeholder="查询模式" class="handle-select mr10">
                    <el-option key="1" label="路线ID查询" value="id"></el-option>
                    <!-- <el-option key="2" label="路线类型查询" value="type"></el-option> -->
                </el-select>
                <el-input v-model="query.options" placeholder="参数" class="handle-input mr10"></el-input>
                <el-button type="primary" icon="el-icon-search" @click="handleSearch">搜索</el-button>
                <el-button type="success" icon="el-icon-plus" @click="handleAdd">添加路线</el-button>
            </div>
            <!-- 路线表单 -->
            <el-table :data="routeData" border class="table" ref="multipleTable" header-cell-class-name="table-header">
                <el-table-column prop="routeId" label="路线ID"  align="center"></el-table-column>
                <el-table-column prop="startStation" label="起始站" align="center"></el-table-column>
                <el-table-column prop="endStation" label="终点站" align="center"></el-table-column>
                <el-table-column label="经停站" align="center">
                    <template  #default="routeData">
                        <el-tag v-if="routeData.row.stopStation == ''" type="info">没有经停站</el-tag>
                        <el-tag v-else v-for="stop in routeData.row.stopStation" :key="stop">{{ stop }}</el-tag>
                    </template>                   
                </el-table-column>
                <!-- 操作栏 -->
                <el-table-column label="操作" width="180" align="center">
                    <template #default="scope">
                        <el-button type="text" icon="el-icon-edit" @click="handleEdit(scope.$index, scope.row)">编辑
                        </el-button>
                        <el-button type="text" icon="el-icon-delete" class="red"
                            @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <!-- 翻页器 -->
            <div class="pagination">
                <el-pagination background layout="total, prev, pager, next" :current-page="query.pageIndex"
                    :page-size="query.pageSize" :total="pageTotal" @current-change="handlePageChange"></el-pagination>
            </div>
        </div>

        <!-- 编辑弹出框 无校验 -->
        <el-dialog title="编辑" v-model="editVisible" width="30%">
            <el-form label-width="100px">
                <el-form-item label="路线ID：">
                    <el-input v-model="form.routeId" disabled placeholder=""></el-input>
                </el-form-item>
                <el-form-item label="始发站：">
                    <el-input v-model="form.startStation" placeholder=""></el-input>
                </el-form-item>
                <el-form-item label="终点站：">
                    <el-input v-model="form.endStation" placeholder=""></el-input>
                </el-form-item>
                <el-form-item label="经停站:">
                    <el-tag v-if="form.stopStation == ''" type="info">没有经停站</el-tag>
                    <el-tag v-else v-for="stop in form.stopStation" :key="stop" class="mx-1" closable
                        :disable-transitions="false" @close="handleClose(stop)">
                        {{ stop }}
                    </el-tag>
                    <el-input v-if="inputVisible" ref="InputRef" v-model="inputValue"  class="ml-1 w-20"  size="small"  
                        @keyup.enter="handleEditStopStation" @blur="handleEditStopStation"/>
                    <el-button v-else class="button-new-tag ml-1" size="small" @click="showInput">
                        + 新建经停站
                    </el-button>
                </el-form-item>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="editVisible = false">取 消</el-button>
                    <el-button type="primary" @click="saveEdit">确 定</el-button>
                </span>
            </template>
        </el-dialog>

        <!-- 添加弹出框 有校验 -->
        <el-dialog title="添加路线" v-model="addVisible" width="30%">
            <el-form :model="ruleForm" ref="ruleFormRef" :rules="addRules" label-width="100px">
            <!-- <el-form rules="rules" label-width="70px"> -->
                <el-form-item label="路线ID：" prop="routeId">
                    <el-input v-model="ruleForm.routeId" placeholder=""></el-input>
                </el-form-item>
                <el-form-item label="始发站：">
                    <el-input v-model="ruleForm.startStation" placeholder=""></el-input>
                </el-form-item>
                <el-form-item label="终点站：">
                    <el-input v-model="ruleForm.endStation" placeholder=""></el-input>
                </el-form-item>
                <el-form-item label="经停站">
                    <el-tag v-for="stop in ruleForm.stopStation" :key="stop" class="mx-1" closable
                        :disable-transitions="false" @close="handleClose(stop)">
                        {{ stop }}
                    </el-tag>
                    <el-input v-if="inputVisible" ref="InputRef" v-model="inputValue"  class="ml-1 w-20"  size="small"  
                        @keyup.enter="handleAddStopStation" @blur="handleAddStopStation"/>
                    <el-button v-else class="button-new-tag ml-1" size="small" @click="showInput">
                        + 新建经停站
                    </el-button>
                </el-form-item>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="addVisible = false">取 消</el-button>
                    <el-button type="primary" @click="saveCreate">确 定</el-button>
                </span>
            </template>
        </el-dialog>
    </div>
</template>

<script>

import { ref, reactive } from "vue";
import { ElMessage, ElMessageBox, ElTag } from "element-plus";
import { getDataParam, insertData, deleteData, updateData } from "../../api/index";

export default {
    name: "routeTable",
    setup() {
        // 可视化 相关数据
        const addVisible = ref(false);
        const editVisible = ref(false);
        const inputVisible = ref(false);
        // data 相关数据
        const inputValue = ref("");
        const routeData = ref([]);
        const pageTotal = ref(0);
        const menu = ref([]);
        // request 相关数据
        const path = "/route/queryRoute";
        const query = reactive({
            mode: "id",
            options: "all",
            pageIndex: 1,
            pageSize: 10,
        });
        // 表单
        const form = reactive({
            routeId: "",
            startStation: "",
            endStation: "",
            stopStation: [],
        });
        // 规则校验表单
        const ruleForm = reactive({
            routeId: "",
            startStation: "",
            endStation: "",
            stopStation: [],
        });
        const deleteParam = reactive({
            routeId: "",
        });
        // 表单规则
        const ruleFormRef = ref();
        // 自定义验证规则
        const validatePass = (rule, value, callback) => {
            //  密码只能由大小写英文字母或数字开头，且由大小写英文字母_.组成
            const reg = /^[A-Za-z0-9][A-Za-z0-9_.]{5,14}$/;
            console.log("reg", value.match(reg));
            if (!value.match(reg)) {
                callback(new Error("密码由字母或数字开头，且只能为字母,数字,下划线及（.）"));
            }
            else {
                callback();
            }
        };
        const addRules = reactive({
            routeId: [
                { required: true, message: "请输入路线ID", trigger: "blur" },
            ],
            startStation: [
                { required: true, message: "请输入路线！", trigger: "blur" },
                { min: 2, max: 20, message: "名称长度应介于2到20之间", trigger: "blur" },
                { type: "string", message: "请输入汉字！", trigger: "blur" },
            ],
            endStation: [
                { required: true, message: "请输入路线！", trigger: "blur" },
                { min: 2, max: 20, message: "名称长度应介于2到20之间", trigger: "blur" },
                { type: "string", message: "请输入汉字！", trigger: "blur" },
            ],
        });
        /** 定义方法 */
        // 获取表格数据
        const getFormData = () => {
            getDataParam(query, path).then((res) => {
                console.log(res.data)
                routeData.value = changeData(res.data);
                pageTotal.value = res.pageTotal || 10;
            });
        };

        // 添加路线数据
        const addRouteData = (data) => {
            insertData(data, "/route/createRoute").then((res) => {
                console.log(res.data);
            });
        };

        // 更新路线数据
        const updateRouteData = (data) => {
            updateData(data, "/route/updateRoute").then((res) => {
                console.log(res.data);
                // refresh;
            });
        };

        // 删除路线数据
        const deleteRouteData = (data) => {
            deleteData(data, "/route/deleteRoute").then((res) => {
                console.log(res.data);
                // refresh;
            });
        };

        /**  数据加工方法  */
        // 更新stop station为数组类型
        const changeData = (data) => {
            console.log(data);
            for (var i = 0; i < data.length; i++) {
                var stop = String(data[i].stopStation);
                data[i].stopStation = stop.split(",");
            }
            return data;
        };
        /** 按钮事件  */
        // 分页导航
        const handlePageChange = (val) => {
            query.pageIndex = val;
            getFormData();
        };
        // 查询操作
        const handleSearch = () => {
            query.pageIndex = 1;
            console.log(query)
            getFormData();
        };
        // 添加操作
        const handleAdd = () => {
            addVisible.value = true;
        };
        // 编辑操作
        let idx = -1;
        const handleEdit = (index, row) => {
            idx = index;
            Object.keys(form).forEach((item) => {
                form[item] = row[item];
                console.log(form[item]);
            });
            editVisible.value = true;
        };
        // 删除操作
        const handleDelete = (index, row) => {
            // 二次确认删除
            ElMessageBox.confirm("确定要删除吗？", "提示", {
                type: "warning",
            })
                .then(() => {
                deleteParam.routeId = row.routeId;
                deleteRouteData(deleteParam);
                ElMessage.success("删除成功");
                getFormData();
            })
                .catch(() => { });
        };
        // 保存新增内容 
        const saveCreate = () => {
            addVisible.value = false;
            console.log(ruleForm);
            ruleForm.stopStation = ruleForm.stopStation.join(",")
            addRouteData(ruleForm);
            ElMessage.success(`添加新用户成功`);
            getFormData();
        };
        // 保存编辑内容

        const saveEdit = () => {
            editVisible.value = false;
            Object.keys(form).forEach((item) => {
                routeData.value[idx][item] = form[item];
            });
            routeData.value[idx].stopStation = routeData.value[idx].stopStation.join(",")
            console.log(routeData.value[idx]);
            updateRouteData(routeData.value[idx]);
            ElMessage.success(`修改第 ${idx + 1} 行成功`);
            getFormData();
        };
        // 关闭经停站标签
        const handleClose = (tag) => {
            form.stopStation.splice(form.stopStation.indexOf(tag), 1);
        };
        // 显示输入框
        const showInput = () => {
            inputVisible.value = true;
        };

        // 处理edit经停站
        const handleEditStopStation= () => {
            if (inputValue.value) {
                form.stopStation.push(inputValue.value);
            }
            inputVisible.value = false;
            inputValue.value = "";
        };

        // 处理add经停站
        const handleAddStopStation= () => {
            if (inputValue.value) {
                ruleForm.stopStation.push(inputValue.value);
            }
            inputVisible.value = false;
            inputValue.value = "";
        };

        // setup时执行的函数
        getFormData();

        return {
            query,
            routeData,
            pageTotal,
            editVisible,
            addVisible,
            inputVisible,
            inputValue,
            form,
            ruleForm,
            ruleFormRef,
            deleteParam,
            addRules,
            menu,
            handlePageChange,
            handleDelete,
            handleSearch,
            handleAdd,
            saveCreate,
            handleEdit,
            saveEdit,
            handleClose,
            showInput,
            handleEditStopStation,
            handleAddStopStation,
        };
    },
    components: { ElTag }
};
</script>

<style scoped>
.handle-box {
    margin-bottom: 20px;
}

.handle-select {
    width: 200px;
}

.handle-input {
    width: 300px;
    display: inline-block;
}
.table {
    width: 100%;
    font-size: 14px;
}
.red {
    color: #ff0000;
}
.mr10 {
    margin-right: 10px;
}
.table-td-thumb {
    display: block;
    margin: auto;
    width: 40px;
    height: 40px;
}
</style>
