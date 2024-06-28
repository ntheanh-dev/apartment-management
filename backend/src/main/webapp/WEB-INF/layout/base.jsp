<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><tiles:insertAttribute name="title" /></title>

        <%-----------------Google Fonts----------------%>
        <link href="https://fonts.gstatic.com" rel="preconnect"/>
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet"/>

        <%-----------------Bootstrap----------------%>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">

        <%-----------------Tailwind css----------------%>
        <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet" />
        <script src="https://cdn.tailwindcss.com"></script>

        <%-----------------MomentJs----------------%>
        <script src="<c:url value="/js/moment.js"/>"></script>

        <%-----------------Css----------------%>
        <link rel="stylesheet" href="<c:url value="/css/style.css"/>" ></link>

        <%-----------------Jquery----------------%>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

        <%-----------------SweetAlert2----------------%>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

        <%-----------------Firebase----------------%>

        <script src="https://www.gstatic.com/firebasejs/9.13.0/firebase-app-compat.js"></script>
        <script src="https://www.gstatic.com/firebasejs/9.13.0/firebase-auth-compat.js"></script>
        <script src="https://www.gstatic.com/firebasejs/ui/6.0.2/firebase-ui-auth.js"></script>
        <%-----------------Chart----------------%>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    </head>
    <body>
        <script type="module">
            import { initializeApp } from "https://www.gstatic.com/firebasejs/10.12.2/firebase-app.js";
            import { getStorage ,ref, uploadBytesResumable, getDownloadURL} from "https://www.gstatic.com/firebasejs/10.12.2/firebase-storage.js";
            const firebaseConfig = {
                apiKey: "${apiKey}",
                authDomain: "${authDomain}",
                projectId: "${projectId}",
                storageBucket: "${storageBucket}",
                messagingSenderId: "${messagingSenderId}",
                appId: "${appId}"
            };

            // Initialize Firebase
            const app = initializeApp(firebaseConfig);
            const storage = getStorage(app);
        </script>
        <%--  Header      --%>
        <tiles:insertAttribute name="header" />
        <%--  Sidebar     --%>
        <tiles:insertAttribute name="sidebar" />
        <main id="main" class="main p-0 sm:p-5">
            <div class="bg-white sm:p-4" style="min-height:calc(100vh - 65px);">
                <tiles:insertAttribute name="content" />
            </div>
        </main>

        <script src="<c:url value="/js/index.js"/>"></script>
    </body>
</html>