import request from '../utils/http.js';

// 插入数据
export const insertData = (data,path) => {
    return request({
        url: 'http://localhost:8080' + path,
        method: 'post',
        data
    })
}
// 无参数插入数据
export const insertDataParam = (data,path) => {
    return request({
        url: 'http://localhost:8080' + path,
        method: 'post',
        data
    })
}
// 删除数据
export const deleteData = (data,path) => {
    return request({
        url: 'http://localhost:8080' + path,
        method: 'post',
        data
    })
}
// 更新数据
export const updateData = (data ,path) => {
    return request({
        url: 'http://localhost:8080' + path,
        method: 'post',
        data
    })
}
// 获取数据
export const getDataParam = (data, path) => {
    return request({
        url: 'http://localhost:8080' + path,
        method: 'get',
        data
    })
	
};

export const getDataNoParam = (path) => {
    return request({
        url: 'http://localhost:8080' + path,
        method: 'get',

    })
};
