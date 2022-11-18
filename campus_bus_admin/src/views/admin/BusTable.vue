<template>
    <div>
        <!-- 小页面标题 -->
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-lx-cascades"></i> 校车信息管理
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <!-- 校车表单 -->
        <div class="container">
            <!-- 标题工具栏 -->
            <div class="handle-box">
                <el-select v-model="query.mode" placeholder="查询模式" class="handle-select mr10">
                    <el-option key="1" label="校车ID查询" value="id"></el-option>
                    <el-option key="2" label="校车类型查询" value="type"></el-option>
                </el-select>
                <el-input  v-model="query.options" placeholder="参数" class="handle-input mr10"></el-input>
                <el-button type="primary" icon="el-icon-search" @click="handleSearch">搜索</el-button>
                <el-button type="success" icon="el-icon-plus" @click="handleAdd">添加校车</el-button>
            </div>
            <!-- 校车表单 -->
            <el-table :data="busData" border class="table" ref="multipleTable" header-cell-class-name="table-header">
                <el-table-column prop="busId" label="校车ID"  align="center"></el-table-column>
                <el-table-column prop="busName" label="校车名称" align="center"></el-table-column>
                <el-table-column label="校车图片" align="center">
                    <template #default="routeData">
                        <el-image class="table-td-thumb" :src="routeData.row.busImage" :preview-src-list="[routeData.row.busImage]">
                        </el-image>
                    </template>
                </el-table-column>
                <el-table-column prop="busType" label="校车类型" align="center"></el-table-column>
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
                <el-form-item label="校车ID：">
                    <el-input v-model="form.busId" disabled placeholder=""></el-input>
                </el-form-item>
                <el-form-item label="校车名称：">
                    <el-input v-model="form.busName" placeholder=""></el-input>
                </el-form-item>
                <el-form-item label="校车图片：">
                    <el-input v-model="form.busImage" placeholder=""></el-input>
                </el-form-item>
                <el-form-item label="校车类型：">
                    <el-input v-model="form.busType" placeholder=""></el-input>
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
        <el-dialog title="添加校车" v-model="addVisible" width="30%">
            <el-form :model="ruleForm" ref="ruleFormRef" :rules="addRules" label-width="100px">
            <!-- <el-form rules="rules" label-width="70px"> -->
                <el-form-item label="校车ID：" prop="busId">
                    <el-input v-model="ruleForm.busId" placeholder=""></el-input>
                </el-form-item>
                <el-form-item label="校车名称：" prop="busName">
                    <el-input v-model="ruleForm.busName" placeholder=""></el-input>
                </el-form-item>
                <el-form-item label="校车图片：" prop="busImage">
                    <el-input v-model="ruleForm.busImage" placeholder=""></el-input>
                </el-form-item>
                <el-form-item label="校车类型：" prop="busType">
                    <el-input v-model="ruleForm.busType" placeholder=""></el-input>
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
import { ElMessage, ElMessageBox } from "element-plus";
import { getDataNoParam, getDataParam, insertData, deleteData, updateData } from "../../api/index";

export default {
    name: "busTable",
    setup() {
        // 可视化 相关数据
        const addVisible = ref(false);
        const editVisible = ref(false);
        // data 相关数据
        const busData = ref([]);
        const pageTotal = ref(0);
        const menu = ref([]);
        // 设定行列的初值
        const row = ref(4);
        const column = ref(9);
        // request 相关数据
        const path = "/bus/queryBus";
        const query = reactive({
            mode:"id",
            options:"all",          
            pageIndex:1,
            pageSize:10,
        });
        // 表单
        const form = reactive({
            busId:"",
            busName: "",
            busImage: "",
            busType: "",
        });
        // 规则校验表单
        const ruleForm = reactive({
            busId:"",
            busName: "",
            busImage: "",
            busType: "",
        });

        const deleteParam = reactive({
             busId:"",
        })

        // 表单规则
        const ruleFormRef = ref()
        // 自定义验证规则
        const validatePass = (rule, value, callback) => {
            //  密码只能由大小写英文字母或数字开头，且由大小写英文字母_.组成
            const reg = /^[A-Za-z0-9][A-Za-z0-9_.]{5,14}$/
            console.log('reg', value.match(reg))
            if (!value.match(reg)) {
                callback(new Error('密码由字母或数字开头，且只能为字母,数字,下划线及（.）'))
            } else {
                callback()
            }
        }

        const addRules = reactive({
            busId: [
                { required: true, message: '请输入校车ID', trigger: 'blur' },
            ],

            busName: [
                { required: true, message: '请输入校车！', trigger: 'blur' },
                { min: 2, max: 20, message: '名称长度应介于2到20之间', trigger: 'blur' },
                { type: 'string', message: '请输入汉字！', trigger: 'blur' },
            ],
        })

        /** 定义方法 */ 

        // 获取表格数据
        const getFormData = () => {
            getDataParam(query,path).then((res) => {
                console.log(res)
                busData.value = res.data
                pageTotal.value = res.pageTotal || 10
            });
        };

        // 添加校车数据
        const addBusData = (data) => {
            insertData(data,"/bus/createBus").then((res) => {
                console.log(res.data);
            });
        }
        // 更新校车数据
        const updateBusData = (data) => {
            updateData(data,"/reg/updateBus").then((res) => {
                console.log(res.data)
                // refresh;
            });
        }

        // 删除校车数据
        const deleteBusData = (data) => {
            deleteData(data,"/bus/deleteBus").then((res) => {
                console.log(res.data)
                // refresh;
            });
        }

        /** 按钮事件  */

        // 分页导航
        const handlePageChange = (val) => {
            query.pageIndex = val;
            getFormData();
        };
        // 添加操作
        const handleAdd = () => {
            addVisible.value = true;
        }

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
                deleteParam.busId = row.busId
                deleteBusData(deleteParam);
                ElMessage.success("删除成功");
                getFormData();
            })
            .catch(() => {});
        };

        // 查询操作
        const handleSearch = () => {
            query.pageIndex = 1;
            getFormData();
        };

        // 保存新增内容 
        const saveCreate = () => {
            addVisible.value = false;
            console.log(ruleForm)
            addBusData(ruleForm);
            ElMessage.success(`添加新用户成功`);
            getFormData();
        }

        // 保存编辑内容
        const saveEdit = () => {
            editVisible.value = false;
            Object.keys(form).forEach((item) => {
                busData.value[idx][item] = form[item];
            });
            console.log(busData.value[idx])
            updateBusData(busData.value[idx]);
            ElMessage.success(`修改第 ${idx + 1} 行成功`);
            getFormData();
        };
        // setup时执行的函数
        getFormData();

        return {
            query,
            busData,
            pageTotal,
            editVisible,
            addVisible,
            form,
            ruleForm,
            ruleFormRef,
            deleteParam,
            addRules,
            menu,
            row,
            column,
            handlePageChange,
            handleDelete,
            handleSearch,
            handleAdd,
            saveCreate,
            handleEdit,
            saveEdit,
        };
    },
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
