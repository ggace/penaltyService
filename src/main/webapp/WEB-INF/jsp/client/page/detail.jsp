<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="디테일 페이지" />
<%@include file="../../client/common/header.jspf" %>

<script src="/script/detail.js"></script>
<link rel="stylesheet" href="/script/detail.css" />

<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.bundle.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.min.css">

<div style="margin: 30px">
	<h1 style="text-align : center">Title : <span id="title"></span></h1>
</div>
<section id="info">
	
	<span id="adminName"></span>
	
	<div id="chart">
		<iframe name="NeBoard" id="chart-iframe" scrolling="No" src="/client/chart?roomId=${id}" ></iframe>
	</div>

</section>

<div id="root" style="display:flex; justify-content:center; margin-bottom: 10px">
	<div class="loading-container">
	    <div class="loading"></div>
	    <div id="loading-text">loading</div>
	</div>
</div>

<script type="text/babel" src="/script/Log.js" ></script>

<script type="text/babel">
	const roomId = ${id}
	
	var data = undefined;
	
	fetch("/admin/room/getRoomWithAdminInfo?id=" + roomId)
	.then(res => res.json())
	.then(
    	(result) => {
      		data = result.data
      		
      		$("#title").text(data.title)
			$("#adminName").text("admin : " + data.adminName)
		},
    
  	);
  	
  	fetch("/admin/log/getLogsInRoomWithExtra?roomId=" + roomId)
	.then(res => res.json())
	.then(
    	(result) => {
      		const elementMain = result.data.map((log)=>{
      			return <Log logId={log.id} user={log.nickname} content={log.content} money={log.money} regDate={log.regDate}></Log>
      		});

			const root = document.getElementById('root');
			ReactDOM.render(<div id="logList"><Log id="logTitle" logId="id" user="유저" content="내용" money="액수" regDate="등록날짜"></Log>{elementMain}</div>, root);
		}
  	)

	
  	
</script>


</body>
</html>