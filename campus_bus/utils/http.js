let server_url='';//请求地址

process.env.NODE_ENV === 'development' ? 'http://localhost:8080' : 'http://localhost:8080' ; //环境配置
function service(options = {}) {
	options.url = `${server_url}${options.url}`;
	//配置请求头
	options.header = {
		'content-type': 'application/json',
	};
    return new Promise((resolved, rejected) => {
		options.success = (res) => {
			if (Number(res.data.code) == 0) { 
				resolved(res.data);
			} else {
				uni.showToast({
					icon: 'none',   
					duration: 3000,
					title: `${res.data.msg}`
				});
				rejected(res.data.msg);
			}
		}
		options.fail = (err) => {
			rejected(err); 
		}
		
		uni.request(options);
    });
}
export default service;