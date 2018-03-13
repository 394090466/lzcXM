package com.lzc.liu.lzcproject.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by liu on 2018/2/26.
 */

public class NewDataBean {


    @SerializedName("data")
    private DataBean _$Data114; // FIXME check this code
    private String message;

    public DataBean get_$Data114() {
        return _$Data114;
    }

    public void set_$Data114(DataBean _$Data114) {
        this._$Data114 = _$Data114;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        @SerializedName("add_ss_queries_header_open")
        private int _$Add_ss_queries_header_open328; // FIXME check this code
        private int add_ss_queries_open;
        private int add_ss_queries_plaintext_open;
        private int chromium_open;
        private int detect_open;
        private int disable_encrypt_switch;
        private int disable_framed_transport;
        private int get_network_interval;
        private int hs_open;
        private int http_dns_enabled;
        private int http_show_hijack;
        private int http_to_https;
        private int https_dns_err_max;
        private int https_retry_http;
        private int https_to_http;
        private int i_host_max_fail;
        private int i_host_select_interval;
        private int i_host_select_open;
        private int i_host_select_open_v2;
        private int image_ttnet_enabled;
        private MappingBean mapping;
        private int ok_http3_open;
        private int ok_http_open;
        private TcBean tc;
        private int ttnet_http_dns_timeout;
        private int use_http_dns;
        private List<String> frontier_urls;
        private List<String> https_dns;
        private List<IHostListBean> i_host_list;
        private List<String> ttnet_http_dns_bypass_domains;
        private List<String> varticle_frontier_urls;

        public int get_$Add_ss_queries_header_open328() {
            return _$Add_ss_queries_header_open328;
        }

        public void set_$Add_ss_queries_header_open328(int _$Add_ss_queries_header_open328) {
            this._$Add_ss_queries_header_open328 = _$Add_ss_queries_header_open328;
        }

        public int getAdd_ss_queries_open() {
            return add_ss_queries_open;
        }

        public void setAdd_ss_queries_open(int add_ss_queries_open) {
            this.add_ss_queries_open = add_ss_queries_open;
        }

        public int getAdd_ss_queries_plaintext_open() {
            return add_ss_queries_plaintext_open;
        }

        public void setAdd_ss_queries_plaintext_open(int add_ss_queries_plaintext_open) {
            this.add_ss_queries_plaintext_open = add_ss_queries_plaintext_open;
        }

        public int getChromium_open() {
            return chromium_open;
        }

        public void setChromium_open(int chromium_open) {
            this.chromium_open = chromium_open;
        }

        public int getDetect_open() {
            return detect_open;
        }

        public void setDetect_open(int detect_open) {
            this.detect_open = detect_open;
        }

        public int getDisable_encrypt_switch() {
            return disable_encrypt_switch;
        }

        public void setDisable_encrypt_switch(int disable_encrypt_switch) {
            this.disable_encrypt_switch = disable_encrypt_switch;
        }

        public int getDisable_framed_transport() {
            return disable_framed_transport;
        }

        public void setDisable_framed_transport(int disable_framed_transport) {
            this.disable_framed_transport = disable_framed_transport;
        }

        public int getGet_network_interval() {
            return get_network_interval;
        }

        public void setGet_network_interval(int get_network_interval) {
            this.get_network_interval = get_network_interval;
        }

        public int getHs_open() {
            return hs_open;
        }

        public void setHs_open(int hs_open) {
            this.hs_open = hs_open;
        }

        public int getHttp_dns_enabled() {
            return http_dns_enabled;
        }

        public void setHttp_dns_enabled(int http_dns_enabled) {
            this.http_dns_enabled = http_dns_enabled;
        }

        public int getHttp_show_hijack() {
            return http_show_hijack;
        }

        public void setHttp_show_hijack(int http_show_hijack) {
            this.http_show_hijack = http_show_hijack;
        }

        public int getHttp_to_https() {
            return http_to_https;
        }

        public void setHttp_to_https(int http_to_https) {
            this.http_to_https = http_to_https;
        }

        public int getHttps_dns_err_max() {
            return https_dns_err_max;
        }

        public void setHttps_dns_err_max(int https_dns_err_max) {
            this.https_dns_err_max = https_dns_err_max;
        }

        public int getHttps_retry_http() {
            return https_retry_http;
        }

        public void setHttps_retry_http(int https_retry_http) {
            this.https_retry_http = https_retry_http;
        }

        public int getHttps_to_http() {
            return https_to_http;
        }

        public void setHttps_to_http(int https_to_http) {
            this.https_to_http = https_to_http;
        }

        public int getI_host_max_fail() {
            return i_host_max_fail;
        }

        public void setI_host_max_fail(int i_host_max_fail) {
            this.i_host_max_fail = i_host_max_fail;
        }

        public int getI_host_select_interval() {
            return i_host_select_interval;
        }

        public void setI_host_select_interval(int i_host_select_interval) {
            this.i_host_select_interval = i_host_select_interval;
        }

        public int getI_host_select_open() {
            return i_host_select_open;
        }

        public void setI_host_select_open(int i_host_select_open) {
            this.i_host_select_open = i_host_select_open;
        }

        public int getI_host_select_open_v2() {
            return i_host_select_open_v2;
        }

        public void setI_host_select_open_v2(int i_host_select_open_v2) {
            this.i_host_select_open_v2 = i_host_select_open_v2;
        }

        public int getImage_ttnet_enabled() {
            return image_ttnet_enabled;
        }

        public void setImage_ttnet_enabled(int image_ttnet_enabled) {
            this.image_ttnet_enabled = image_ttnet_enabled;
        }

        public MappingBean getMapping() {
            return mapping;
        }

        public void setMapping(MappingBean mapping) {
            this.mapping = mapping;
        }

        public int getOk_http3_open() {
            return ok_http3_open;
        }

        public void setOk_http3_open(int ok_http3_open) {
            this.ok_http3_open = ok_http3_open;
        }

        public int getOk_http_open() {
            return ok_http_open;
        }

        public void setOk_http_open(int ok_http_open) {
            this.ok_http_open = ok_http_open;
        }

        public TcBean getTc() {
            return tc;
        }

        public void setTc(TcBean tc) {
            this.tc = tc;
        }

        public int getTtnet_http_dns_timeout() {
            return ttnet_http_dns_timeout;
        }

        public void setTtnet_http_dns_timeout(int ttnet_http_dns_timeout) {
            this.ttnet_http_dns_timeout = ttnet_http_dns_timeout;
        }

        public int getUse_http_dns() {
            return use_http_dns;
        }

        public void setUse_http_dns(int use_http_dns) {
            this.use_http_dns = use_http_dns;
        }

        public List<String> getFrontier_urls() {
            return frontier_urls;
        }

        public void setFrontier_urls(List<String> frontier_urls) {
            this.frontier_urls = frontier_urls;
        }

        public List<String> getHttps_dns() {
            return https_dns;
        }

        public void setHttps_dns(List<String> https_dns) {
            this.https_dns = https_dns;
        }

        public List<IHostListBean> getI_host_list() {
            return i_host_list;
        }

        public void setI_host_list(List<IHostListBean> i_host_list) {
            this.i_host_list = i_host_list;
        }

        public List<String> getTtnet_http_dns_bypass_domains() {
            return ttnet_http_dns_bypass_domains;
        }

        public void setTtnet_http_dns_bypass_domains(List<String> ttnet_http_dns_bypass_domains) {
            this.ttnet_http_dns_bypass_domains = ttnet_http_dns_bypass_domains;
        }

        public List<String> getVarticle_frontier_urls() {
            return varticle_frontier_urls;
        }

        public void setVarticle_frontier_urls(List<String> varticle_frontier_urls) {
            this.varticle_frontier_urls = varticle_frontier_urls;
        }

        public static class MappingBean {
        }

        public static class TcBean {
        }

        public static class IHostListBean {
            /**
             * host : lf.snssdk.com
             * weight_time : 0
             * max_time : 5000
             */

            private String host;
            private int weight_time;
            private int max_time;

            public String getHost() {
                return host;
            }

            public void setHost(String host) {
                this.host = host;
            }

            public int getWeight_time() {
                return weight_time;
            }

            public void setWeight_time(int weight_time) {
                this.weight_time = weight_time;
            }

            public int getMax_time() {
                return max_time;
            }

            public void setMax_time(int max_time) {
                this.max_time = max_time;
            }
        }
    }
}
