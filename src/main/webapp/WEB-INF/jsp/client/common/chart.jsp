<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="차트 페이지" />

        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.bundle.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.min.css">



<style>
	.chart-div {
		width: 100%;
		height: 100%;
		display: flex;
		justify-content: center;
	}
    .legend-div {
        position: absolute;
        top: 25%;
        left: 400px;
        
    }

    .legend-div ul li {
        margin: 10px 0;
        color: #666;
        font-family: Cambria, Cochin, Georgia, Times, 'Times New Roman', serif
    }

    .legend-div ul li span {
        display: inline-block;
        width: 20px;
        height: 20px;
        border-radius: 50%;
        margin-right: 5px;
        vertical-align: middle;
    }
    
    .legend-div ul {
    	list-style: none;
    }
    
    #pieChartCanvas{
    
    	width: 90vw;
    	height: 90vh
    }
</style>

<script type="text/javascript">
var roomId = ${roomId};
window.onload = function () {
	fetch("/admin/log/getSumOfMoneyLogsInRoomByUserId?roomId=" + roomId)
	.then(res => res.json())
	.then(
    	(result) => {
      		label = [];
      		data = [];
      		
      		result.data.map((log) => {
				label.push(log.nickname);
				data.push(log.sumOfMoney);
			})
			
			let pieChartData = {
			    labels: label,
			    datasets: [{
			        data: data,
			        backgroundColor: ['rgb(0, 150, 150)',  'rgb(75, 192, 192)', 'rgb(54, 162, 235)', 'rgb(153, 102, 255)', 'rgb(230,230,230)', 'rgb(250, 150, 0)','rgb(250, 230, 0)', 'rgb(200, 200, 0)']
			    }] 
			};
      		
      		pieChartDraw(pieChartData);
		},
    
  	);
	
    
    
}



let pieChartDraw = function (pieChartData) {
    let ctx = document.getElementById('pieChartCanvas').getContext('2d');
    
    window.pieChart = new Chart(ctx, {
        type: 'pie',
        data: pieChartData,
        options: {
            responsive: false,
            legend: {
                display: true
            }
        }
    });
};
</script>
    </head>

    <body>
        <div class="chart-div" >
            <canvas id="pieChartCanvas" style="inline"></canvas>
            
        </div>

        
    </body>
</html>