<template>
    <div>
        <!-- 小页面标题 -->
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-lx-cascades"></i> 排班信息管理
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <!-- 排班表单 -->
        <div class="container">
            <!-- 标题工具栏 -->
            <div class="handle-box">
                <el-select v-model="query.mode" placeholder="查询模式" class="handle-select mr10">
                    <el-option key="1" label="排班ID查询" value="id"></el-option>
                    <el-option key="2" label="发车区间查询" value="location"></el-option>
                    <el-option key="3" label="始发站查询" value="startLocation"></el-option>
                    <el-option key="4" label="终点查询" value="endLocation"></el-option>    
                </el-select>
                <el-input v-if="query.mode == 'id'" v-model="query.options" placeholder="参数" class="handle-input mr10"></el-input>
                <el-select v-if="query.mode == 'location' ||query.mode == 'startLocation'" v-model="query.startLocation" placeholder="始发站" class="handle-select mr10">
                    <el-option v-for="campus in campusData" :key="campus.campusId" :label="campus.campusName" :value="campus.campusName" />
                </el-select>
                <el-select v-if="query.mode == 'location' ||query.mode == 'endLocation'" v-model="query.endLocation" placeholder="终点站" class="handle-select mr10">
                    <el-option v-for="campus in campusData" :key="campus.campusId" :label="campus.campusName" :value="campus.campusName" />
                </el-select>
                <el-button type="primary" icon="el-icon-search" @click="handleSearch">搜索</el-button>
                <el-button type="success" icon="el-icon-plus" @click="handleAdd">添加排班</el-button>
            </div>
            <!-- 排班表单 -->
            <el-table :data="scheduleData" border class="table" ref="multipleTable" header-cell-class-name="table-header">
                <el-table-column prop="scheduleId" label="排班ID"  align="center"></el-table-column>
                <el-table-column prop="startLocation" label="始发校区" align="center"></el-table-column>
                <el-table-column prop="endLocation" label="终点校区" align="center"></el-table-column>
                <el-table-column prop="routeId" label="路线ID" align="center"></el-table-column>
                <el-table-column label="发车时间" align="center">
                    <template #default="scope">
                        <el-tag>
                            {{ scope.row.startTime }}
                        </el-tag>
                    </template>
                </el-table-column>
                <el-table-column prop="date" label="发车星期" align="center">
                    <template #default="scope">
                        <el-tag v-if="scope.row.date == ''" type="info">没有日期</el-tag>
                        <el-tag v-else v-for="day in scope.row.date" :key="day" type="success" round>{{ day }}</el-tag>
                    </template>
                </el-table-column>
                <el-table-column prop="busId" label="车辆ID" align="center"></el-table-column>
                <el-table-column prop="seatInfo" label="座位ID" align="center"></el-table-column>
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
                <el-form-item label="排班ID：">
                    <el-input v-model="form.scheduleId" disabled placeholder=""></el-input>
                </el-form-item>
                <el-form-item label="始发校区：">
                    <el-select v-model="form.startLocation" placeholder="始发校区" class="handle-select mr10">
                        <el-option v-for="data in campusData" :key="data.campusId" :label="data.campusName" :value="data.campusName" />
                    </el-select>
                </el-form-item>
                <el-form-item label="终点校区：">
                    <el-select v-model="form.endLocation" placeholder="终点校区" class="handle-select mr10">
                        <el-option v-for="data in campusData" :key="data.campusId" :label="data.campusName" :value="data.campusName" />
                    </el-select>
                </el-form-item>
                <el-form-item label="路线ID："> 
                    <el-select v-model="form.routeId" placeholder="请选择路线号" class="handle-select mr10">
                        <el-option v-for="route in routeData" :key="route.routeId" :label="route.routeId" :value="route.routeId" />
                    </el-select>
                </el-form-item>
                <el-form-item label="发车时间：">
                    <el-time-select v-model="form.startTime" start="06:00" step="00:10" end="15:30" placeholder="Select time"/>
                </el-form-item>
                <el-form-item label="发车星期：">
                    <el-checkbox-group v-model="form.date" border>
                        <el-checkbox label="1" />
                        <el-checkbox label="2" />
                        <el-checkbox label="3" />
                        <el-checkbox label="4" />
                        <el-checkbox label="5" />
                        <el-checkbox label="6" />
                        <el-checkbox label="7" />
                    </el-checkbox-group>
                </el-form-item>
                <el-form-item label="车辆ID：">
                    <el-select v-model="form.busId" placeholder="终点校区" class="handle-select mr10">
                        <el-option v-for="bus in busData" :key="bus.busId" :label="bus.busName" :value="bus.busId" />
                    </el-select>
                </el-form-item>
                <el-form-item label="座位号：">
                    <el-input v-model="form.seatInfo" placeholder="" disabled></el-input>
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
        <el-dialog title="添加排班" v-model="addVisible" width="30%">
            <el-form :model="ruleForm" ref="ruleFormRef" :rules="addRules" label-width="100px">
                <el-form-item label="排班ID：">
                    <el-input v-model="ruleForm.scheduleId" placeholder=""></el-input>
                </el-form-item>
                <el-form-item label="始发校区：">
                    <el-select v-model="ruleForm.startLocation" placeholder="始发校区" class="handle-select mr10">
                        <el-option v-for="data in campusData" :key="data.campusId" :label="data.campusName" :value="data.campusName" />
                    </el-select>
                </el-form-item>
                <el-form-item label="终点校区：">
                    <el-select v-model="ruleForm.endLocation" placeholder="终点校区" class="handle-select mr10">
                        <el-option v-for="data in campusData" :key="data.campusId" :label="data.campusName" :value="data.campusName" />
                    </el-select>
                </el-form-item>
                <el-form-item label="路线ID："> 
                    <el-select v-model="ruleForm.routeId" placeholder="请选择路线号" class="handle-select mr10">
                        <el-option v-for="route in routeData" :key="route.routeId" :label="route.routeId" :value="route.routeId" />
                    </el-select>
                </el-form-item>
                <el-form-item label="发车时间：">
                    <el-time-select v-model="ruleForm.startTime" start="06:00" step="00:10" end="15:30" placeholder="Select time"/>
                </el-form-item>
                <el-form-item label="发车星期：">
                    <el-checkbox-group v-model="ruleForm.date" border>
                        <el-checkbox label="1" />
                        <el-checkbox label="2" />
                        <el-checkbox label="3" />
                        <el-checkbox label="4" />
                        <el-checkbox label="5" />
                        <el-checkbox label="6" />
                        <el-checkbox label="7" />
                    </el-checkbox-group>
                </el-form-item>
                <el-form-item label="车辆ID：">
                    <el-select v-model="ruleForm.busId" placeholder="终点校区" class="handle-select mr10">
                        <el-option v-for="bus in busData" :key="bus.busId" :label="bus.busName" :value="bus.busId" />
                    </el-select>
                </el-form-item>
                <el-form-item label="座位号：">
                    <el-input v-model="ruleForm.seatInfo" placeholder="" disabled></el-input>
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
import { getDataParam, insertData, deleteData, updateData } from "../../api/index";

export default {
    name: "scheduleTable",
    setup() {
        // 可视化 相关数据
        const addVisible = ref(false);
        const editVisible = ref(false);
        // data 相关数据
        const scheduleData = ref([]);
        const campusData = ref([]);
        const routeData = ref([]);
        const busData = ref([]);
        const pageTotal = ref(0);
        const menu = ref([]);
        const value = ref();
        // request 相关数据
        const path = "/schedule/querySchedule";
        const query = reactive({
            mode:"id",
            options:"all",  
            startLocation:"",
            endLocation:"",        
            pageIndex:1,
            pageSize:10,

        });
        // 表单
        const form = reactive({
            scheduleId:"",
            startLocation: "",
            endLocation: "",
            routeId: "",
            startTime: "",
            date: [],
            busId: "",
            seatInfo: [],
        });
        // 规则校验表单
        const ruleForm = reactive({
            scheduleId:"",
            startLocation: "",
            endLocation: "",
            routeId: "",
            startTime: "",
            date: [],
            busId: "",
            seatInfo: [],
        });
        const deleteParam = reactive({
             scheduleId:"",
        })
        // 表单规则
        const ruleFormRef = ref()
        // 自定义验证规则

        const addRules = reactive({
            scheduleId: [
                { required: true, message: '请输入排班ID', trigger: 'blur' },
            ],

            scheduleName: [
                { required: true, message: '请输入排班！', trigger: 'blur' },
                { min: 2, max: 20, message: '名称长度应介于2到20之间', trigger: 'blur' },
                { type: 'string', message: '请输入汉字！', trigger: 'blur' },
            ],
        })

        /** 定义方法 */ 

        // 获取表格数据
        const getFormData = () => {
            if(query.mode != 'id') {
                query.options = ""
            }
            getDataParam(query,path).then((res) => {
                console.log(res)
                scheduleData.value = changeData(res.data);
                // scheduleData.value = res.data
                pageTotal.value = res.pageTotal || 10
            });
        };
        // 获取校园数据
        const getCampusData = () => {
            getDataParam(query, "/campus/queryCampus").then((res) => {
                console.log(res)
                campusData.value = res.data
                console.log(campusData.value)
                pageTotal.value = res.pageTotal || 10
            });
        };
        // 获取校园数据
        const getRouteData = () => {
            getDataParam(query, "/route/queryRoute").then((res) => {
                console.log(res)
                routeData.value = res.data
                pageTotal.value = res.pageTotal || 10
            });
        };
        // 获取校园数据
        const getBusData = () => {
            getDataParam(query, "/bus/queryBus").then((res) => {
                console.log(res)
                busData.value = res.data
                pageTotal.value = res.pageTotal || 10
            });
        };


        // 添加排班数据
        const addScheduleData = (data) => {
            insertData(data,"/schedule/createSchedule").then((res) => {
                console.log(res.data);
            });
        }
        // 更新排班数据
        const updateScheduleData = (data) => {
            updateData(data,"/reg/updateSchedule").then((res) => {
                console.log(res.data)
                // refresh;
            });
        }

        // 删除排班数据
        const deleteScheduleData = (data) => {
            deleteData(data,"/schedule/deleteSchedule").then((res) => {
                console.log(res.data)
                // refresh;
            });
        }

        /**  数据加工方法  */
        // 更新stop station为数组类型
        const changeData = (data) => {
            console.log(data);
            for (var i = 0; i < data.length; i++) {
                var day = String(data[i].date);
                data[i].date = day.split(",");
            }
            return data;
        };

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
                deleteParam.scheduleId = row.scheduleId
                deleteScheduleData(deleteParam);
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
            ruleForm.date = ruleForm.date.join(",")
            ruleForm.seatInfo = ruleForm.seatInfo.join(",")
            addScheduleData(ruleForm);
            ElMessage.success(`添加新用户成功`);
            getFormData();
        }

        // 保存编辑内容
        const saveEdit = () => {
            editVisible.value = false;
            Object.keys(form).forEach((item) => {
                scheduleData.value[idx][item] = form[item];
            });
            console.log(scheduleData.value[idx])
            updateScheduleData(scheduleData.value[idx]);
            ElMessage.success(`修改第 ${idx + 1} 行成功`);
            getFormData();
        };
        // setup时执行的函数
        getFormData();
        getCampusData();
        getRouteData();
        getBusData();

        return {
            value,
            query,
            scheduleData,
            campusData,
            routeData,
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
