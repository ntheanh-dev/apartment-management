<%--
  Created by IntelliJ IDEA.
  User: An Tien
  Date: 13/06/2024
  Time: 9:58 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="possitive" value="positive"/>
<c:set var="negative" value="negative"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p class="text-3xl font-bold mb-2">Thống kê</p>

<div class="row">
    <div class="col-md-4">
        <div class="card mb-4 shadow">
            <div class="card-body">
                <h5 class="card-title">Số phòng đã được thuê</h5>
                <p class="card-text fw-bolder display-6 text-center">${countRoom}</p>
                <p class="text-success">Tăng 6.65% so với tháng trước</p>
            </div>
        </div>
    </div>
    <div class="col-md-4">
        <div class="card mb-4 shadow">
            <div class="card-body">
                <h5 class="card-title">Tổng số đóng góp trong tháng</h5>
                <p class="card-text fw-bolder display-6 text-center">${countReport}</p>
                <p class="text-danger">Giảm 6.65% so với tháng trước </p>
            </div>
        </div>
    </div>
    <div class="col-md-4">
        <div class="card mb-4 shadow">
            <div class="card-body">
                <h5 class="card-title">Tổng số đánh giá trong tháng</h5>
                <p class="card-text fw-bolder display-6 text-center">${countEvaluation}</p>
                <p class="text-success">Tăng 6.65% so với tháng trước</p>
            </div>
        </div>
    </div>
</div>
<div class="row d-flex align-items-center">
    <div class="col-md-5 p-2 mx-3 my-3 shadow rounded">
        <canvas id="RevenueByMonth"></canvas>
    </div>
    <div class="col-md-6 p-2 mx-3 my-3 shadow rounded">
        <canvas id="StatReport"></canvas>
    </div>
</div>
<script>
    function renderDoughNutChart(ctx, labels, data, label, title) {
        new Chart(ctx, {
            type: 'doughnut',
            data: {
                labels: labels,
                datasets: [{
                    label: label,
                    data: data,
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.2)',
                        'rgba(54, 162, 235, 0.2)',
                        'rgba(255, 206, 86, 0.2)',
                        'rgba(75, 192, 192, 0.2)'
                    ],
                    borderColor: [
                        'rgba(255, 99, 132, 1)',
                        'rgba(54, 162, 235, 1)',
                        'rgba(255, 206, 86, 1)',
                        'rgba(75, 192, 192, 1)'
                    ],
                    borderWidth: 1
                }]
            },
            options: {
                responsive: true,
                plugins: {
                    legend: {
                        position: 'top',
                    },
                    title: {
                        display: true,
                        text: title
                    }
                }
            }
        });
    }

    function renderBarChart(ctx, labels, data, label, title) {
        ctx.canvas.height = 360;
        new Chart(ctx, {
            type: 'bar',
            data: {
                labels: labels,
                datasets: [{
                    label: label,
                    data: data,
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.2)',
                        'rgba(54, 162, 235, 0.2)',
                        'rgba(255, 206, 86, 0.2)',
                        'rgba(75, 192, 192, 0.2)'
                    ],
                    borderColor: 'rgba(75, 192, 192, 1)',
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                },
                plugins: {
                    title: {
                        display: true,
                        text: title
                    }
                }
            }
        });
    }
    let labels = [];
    let data = [];
    <c:forEach items="${statRevenue}" var="p">
        labels.push('tháng ${p[0]}');
        data.push(${p[1]});
    </c:forEach>

    const RevenueByMonth = document.getElementById('RevenueByMonth').getContext('2d');
    renderBarChart(RevenueByMonth, labels, data, 'Đồng', 'Doanh số thu nhập theo tháng');

    let label2 = [];
    let data2 = [];
    <c:forEach items="${statReport}" var="p">
        label2.push("${p[0]}");
        data2.push(${p[1]});
    </c:forEach>

    const StatReport = document.getElementById('StatReport').getContext('2d');
    renderDoughNutChart(StatReport, label2, data2, 'Tháng', 'Thống kê đóng góp của người dân theo tháng');

</script>
</body>
</html>
