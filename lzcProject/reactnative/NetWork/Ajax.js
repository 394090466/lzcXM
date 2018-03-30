ajax = async (config) => {
    let defaultConfig = {
        type: 'get',
        url: '',
        success: () => { },
        error: () => { },
        data: '',
        event:null,
        dataType: 'json'
    };
    let _config = defaultConfig;
    for(var key in config)
    {
        _config[key]=config[key];
    }
    let dataType = {};
    switch (_config.dataType) {
        case 'json': dataType = {
            'Accept': 'application/json',
            'Content-Type': 'text/html',
            'x-requested-with':'XMLHttpRequest'
        }; break;
        case 'html': dataType = {
            'Accept': 'text/html',
            'Content-Type': 'text/html',
            'x-requested-with':'XMLHttpRequest'
        }; break;
        default: dataType = {
            'Accept': 'application/json',
            'Content-Type': 'text/html',
            'x-requested-with':'XMLHttpRequest'
        }
    }
    try {
        let response = await fetch(_config.url, {
            method: _config.type,
            headers: dataType,
            body: _config.data
        });
        let data = null;
        if (_config.dataType == 'json') {
            data = await response.json();
        }
        else {
            data = await response.text();
        }
        _config.success(data,_config.event);
    }
    catch (ex) {
        _config.error(ex,_config.event);
    }
}

module.exports=ajax;