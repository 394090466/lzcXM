export default class NetConfig {
    //协议
    Agreement = "https";
    //主机
    apihost = "minliapp.lzjr888.com";
    //端口
    port = 443;
    //协议主机端口一体
    host = this.Agreement + "://" + this.apihost + ":" + this.port;
    //websocket主机
    wshost = "minliapp.lzjr888.com";
    //websocket端口
    wsport = 443;
    //首页数据接口
    homePage = "/Account/GetAppHome";
 
}