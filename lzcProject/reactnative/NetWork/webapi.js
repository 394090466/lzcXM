import NetConfig from './NetConfig'
import {AsyncStorage} from 'react-native'
export default class Webapi {
    constructor() {
        this.NetConfig = new NetConfig();
    }

    async Ajax(config) {
        let defaultConfig = {
            type: 'get',
            url: '',
            success: () => {
            },
            error: () => {
            },
            data: null,
            event: null,
            dataType: 'json'
        };
        let _config = defaultConfig;
        for (var key in config) {
            _config[key] = config[key];
        }
        var dataType = {};
        switch (_config.dataType) {
            case 'json':
                dataType = {
                    'Accept': 'application/json',
                    'Content-Type': 'application/x-www-form-urlencoded',
                    'x-requested-with': 'XMLHttpRequest',
                };
                break;
            case 'html':
                dataType = {
                    'Accept': 'text/html',
                    'Content-Type': 'application/x-www-form-urlencoded',
                    'x-requested-with': 'XMLHttpRequest',
                };
                break;
            case 'formData':
                dataType = {
                    'Accept': 'application/json',
                    'Content-Type': 'multipart/form-data',
                    'x-requested-with': 'XMLHttpRequest',
                };
                break;
            default:
                dataType = {
                    'Accept': 'application/json',
                    'Content-Type': 'application/x-www-form-urlencoded',
                    'x-requested-with': 'XMLHttpRequest',
                }
        }
        if (_config.cookie != null) {
            dataType['cookie']= _config.cookie;
        }
        let result = "";
        try {
            let response = await fetch(_config.url, {
                method: _config.type,
                headers: dataType,
                mode: "same-origin",
                body: _config.data
            });
            let data = null;
            result = await response.text();
            if (_config.dataType == 'json' || _config.dataType == 'formData') {
                data = JSON.parse(result);
            }
            else {
                data = result;
            }
            let headers = response.headers;
            _config.success(data, _config.event, headers);
        }
        catch (ex) {
            _config.error(ex, _config.event, result);
        }
        // let headers = null;
        // fetch(_config.url, {
        //     method: _config.type,
        //     headers: dataType,
        //     mode: "same-origin",
        //     body: _config.data
        // }).then(Response => {
        //     if (Response.ok) {
        //         headers = Response.headers;
        //         if (_config.dataType == 'json' || _config.dataType == 'formData') {
        //             return Response.json();
        //         }
        //         else {
        //             return Response.text();
        //         }
        //     }
        // }).then(data => {
        //     console.log(data);
        //     _config.success(data, _config.event, headers)
        // }).catch((error,ex) => console.log(error))
    }

    extend(config, newconfig) {
        for (var key in newconfig) {
            config[key] = newconfig[key];
        }
        return config;
    }

    _ajax(config, event) {
        let _default = {
            success: () => {
            },
            error: () => {
            },
            type: 'post',
            url: '',
            data: null,
            event: null,
            dataType: 'json',
            
        }
        let _config = this.extend(_default, config);
        if (config.cookie) {
            _config.cookie = config.cookie;
        }
        this.Ajax({
            url: _config.url,
            type: _config.type,
            dataType: _config.dataType,
            event: event,
            data: _config.data,
            cookie: _config.cookie,
            success: _config.success,
            error: (ex, result, event) => {
                _config.error(ex, result, event);
            }
        });
    }

    getHomeData(config, event, UseCache) {

        let _config = this.extend({}, config);
        _config.successed = config.success;
        _config.success = (result, event) => {
            if (result.state === true) {
                let strdate = JSON.stringify(result);
                AsyncStorage.setItem("getHomeData", strdate, (error) => {
                    // if(error!=null)
                    // {
                    //     //数据保存错误
                    // }
                });
            }
            _config.successed(result, event);
        };
        if (UseCache) {
            AsyncStorage.getItem("getHomeData", (error, result) => {
                if (error === null && result !== null) {
                    let date = JSON.parse(result);
                    _config.success(date, event);
                    this.getHomeData(_config, event, false);
                    return;
                }
                this.getHomeData(_config, event, false);
            });
            return;
        }
        let url = this.NetConfig.Agreement + "://" + this.NetConfig.apihost + ":" + this.NetConfig.port + this.NetConfig.homePage;
        _config.url = url;
        this._ajax(_config, event);
    }

    getkuaixun(config, event) {
        let url = "wss://" + this.NetConfig.wshost + ":" + this.NetConfig.wsport + this.NetConfig.kuaixun;
        let ws = new WebSocket(url);
        ws.onopen = (e) => {
            let timer = setInterval(() => {
                try {
                    ws.send(" ");
                }
                catch (ex) {
                    clearInterval(timer);
                    this.getkuaixun(config, event);
                }
            }, 10000);
        }
        ws.onmessage = (message) => {
            try {
                config.onmessage(JSON.parse(message.data), event, ws);
            }
            catch (ex) {
                config.error(ex, ws)
            }
        }
        ws.onerror = (e) => {
            // 发生了一个错误
            //console.log(e);
        };

        ws.onclose = (e) => {
            // 连接被关闭了
            //console.log(e);
        };
    }

    gethqlist(config, event) {
        let url = this.NetConfig.Agreement + "://" + this.NetConfig.apihost + ":" + this.NetConfig.port + this.NetConfig.hqlist;
        config.url = url;
        this._ajax(config, event);
    }

    getcaijinrili(config, event) {
        let url = this.NetConfig.caijingrili + config.date;
        config.type = "get";
        config.url = url;
        let _config = this.extend({}, config);
        _config.successed = config.success;

        AsyncStorage.getItem("caijingrili" + config.date, (error, result) => {
            if (error === null && result !== null) {
                let date = JSON.parse(result);
                _config.success(date, event);
                return;
            }
            _config.success = (result, event) => {
                if (result.length !== 0 && result[0].index_name !== undefined) {
                    let strdate = JSON.stringify(result);
                    AsyncStorage.setItem("caijingrili" + config.date, strdate, (error) => {
                        // if(error!=null)
                        // {
                        //     //数据保存错误
                        // }
                    });
                }
                _config.successed(result, event);
            };
            this._ajax(_config, event);
        })
    }

    getlivelist(config, event) {
        let url = this.NetConfig.Agreement + "://" + this.NetConfig.apihost + ":" + this.NetConfig.port + this.NetConfig.livelist;
        config.url = url;
        this._ajax(config, event);
    }

    login(config, event) {
        let url = this.NetConfig.Agreement + "://" + this.NetConfig.apihost + ":" + this.NetConfig.port + this.NetConfig.login;
        config.url = url;
        this._ajax(config, event);
    }

    getuserinfo(config, event) {
        let url = this.NetConfig.Agreement + "://" + this.NetConfig.apihost + ":" + this.NetConfig.port + this.NetConfig.userinfo;
        config.url = url;
        this._ajax(config, event);
    }

    getInfomationList(config, event) {
        let url = this.NetConfig.Agreement + "://" + this.NetConfig.apihost + ":" + this.NetConfig.port + this.NetConfig.articleList;
        config.data = "Page=" + config.page;
        config.url = url;
        this._ajax(config, event);
    }

    getCollectionArticlelist(config, event) {
        let url = this.NetConfig.Agreement + "://" + this.NetConfig.apihost + ":" + this.NetConfig.port + this.NetConfig.collectionArticlelist;
        config.url = url;
        config.data ="Page=" + config.page;
        this._ajax(config, event);
    }

    RevokeCollectArticle(config,event){
        let url = this.NetConfig.Agreement + "://" + this.NetConfig.apihost + ":" + this.NetConfig.port + this.NetConfig.RevokeCollectArticle;
        config.url = url;
        config.data ="ID=" + config.ID;
        this._ajax(config, event);
    }

    upLoadHeadimg(config, event) {
        let url = this.NetConfig.Agreement + "://" + this.NetConfig.apihost + ":" + this.NetConfig.port + this.NetConfig.uploadHeadimg;
        config.url = url;
        this._ajax(config, event);
    }

    editNickName(config, event) {
        let url = this.NetConfig.Agreement + "://" + this.NetConfig.apihost + ":" + this.NetConfig.port + this.NetConfig.editNickName;
        config.url = url;
        this._ajax(config, event);
    }

    sendRegistrationCode(config, event) {
        let url = this.NetConfig.Agreement + "://" + this.NetConfig.apihost + ":" + this.NetConfig.port + this.NetConfig.sendRegistrationCode;
        config.url = url;
        this._ajax(config, event);
    }

    verificationValidateCode(config, event) {
        let url = this.NetConfig.Agreement + "://" + this.NetConfig.apihost + ":" + this.NetConfig.port + this.NetConfig.verificationValidateCode;
        config.url = url;
        this._ajax(config, event);
    }

    completeRegister(config, event) {
        let url = this.NetConfig.Agreement + "://" + this.NetConfig.apihost + ":" + this.NetConfig.port + this.NetConfig.completeRegister;
        config.url = url;
        this._ajax(config, event);
    }

    aForgotPassword(config, event) {
        let url = this.NetConfig.Agreement + "://" + this.NetConfig.apihost + ":" + this.NetConfig.port + this.NetConfig.aForgotPassword;
        config.url = url;
        this._ajax(config, event);
    }

    aResetPassword(config, event) {
        let url = this.NetConfig.Agreement + "://" + this.NetConfig.apihost + ":" + this.NetConfig.port + this.NetConfig.aResetPassword;
        config.url = url;
        this._ajax(config, event);
    }

    EnjoyservesList(config, event) {
        let url = this.NetConfig.Agreement + "://" + this.NetConfig.apihost + ":" + this.NetConfig.port + this.NetConfig.EnjoyservesList;
        config.url = url;
        config.data = "Page=" + config.page;
        this._ajax(config, event);
    }

    GetInvestSchoollist(config, event) {
        let url = this.NetConfig.Agreement + "://" + this.NetConfig.apihost + ":" + this.NetConfig.port + this.NetConfig.GetInvestSchoollist;
        config.url = url;
        this._ajax(config, event);
    }

    FeedBackAdd(config, event) {
        let url = this.NetConfig.Agreement + "://" + this.NetConfig.apihost + ":" + this.NetConfig.port + this.NetConfig.FeedBackAdd;
        config.url = url;
        this._ajax(config, event);
    }
}