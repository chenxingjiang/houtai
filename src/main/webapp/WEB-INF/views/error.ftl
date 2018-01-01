<#include "common.ftl" >
 <script>
    $(function(){
       /**
          errorMsg
          code
       **/
       alert("${errorMsg}");
       if("${uri}"=="/main"){
          window.location.href="${ctx}/index";
       }else{
         window.parent.location.href="${ctx}/index";
       }
    })
 </script>