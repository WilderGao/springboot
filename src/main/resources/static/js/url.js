//获取URL的参数和设置参数
(function($) {
    $.extend({
        save: function(key,value){
            sessionStorage.setItem(key,value);
        },
        search: function(key){
            var value = sessionStorage.getItem(key);
            // console.log(value);
            return value;
        },
        clear: function(){
            sessionStorage.clear();
        }
    });
})(jQuery);

(function($) {
    $.extend({
        localUrl: function () {
            return "http://localhost:8080/";
        },
        login: function (json) {
            var xhr = $.ajax({
                url: $.localUrl()+"user/login",
                type: "POST",
                contentType: 'application/json;charset=UTF-8',
                async: false,
                xhrFields: {
                    withCredentials: true
                },
                data: JSON.stringify(json)
            });
            var result = JSON.parse(xhr.responseText);
            return result;
        },
        information: function () {
            var xhr = $.ajax({
                url: $.localUrl()+"user/information",
                type: "GET",
                async: false,
                xhrFields: {
                    withCredentials: true
                }
            });
            var result = JSON.parse(xhr.responseText);
            return result;
        },
        allMember: function () {
            var xhr = $.ajax({
                url: $.localUrl()+"user/members",
                type: "GET",
                async: false,
                xhrFields: {
                    withCredentials: true
                }
            });
            return JSON.parse(xhr.responseText);
        }
    });
})(jQuery)