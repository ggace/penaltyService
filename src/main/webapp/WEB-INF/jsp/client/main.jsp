<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="메인 페이지" />
<%@include file="../client/common/header.jspf" %>



<div id="root" style="margin-top: 24px; margin-left: 24px"></div>

<script type="text/babel" src="/script/Room.js" ></script>

<script type="text/babel">
	
	fetch("/client/room/getRoomByUserId")
		.then(res => res.json())
		.then(
        	(result) => {
          		const elementMain = result.data.map((room) => {
					return <Room title={room.title} adminName={room.adminName}></Room>
				});
				const elementSideBar = result.data.map((room) => {
					return <a href="#" class="w3-bar-item w3-button truncate">{room.title} - {room.adminName}</a>
				});
				
				const root = document.getElementById('root');
				ReactDOM.render(<div style={{display: "flex", flexWrap: "wrap"}}>{elementMain}</div>, root);
				const sideBarRoot = document.getElementById('sidebarRoot');				
				ReactDOM.render(<div style={{display: "flex", flexWrap: "wrap"}}>{elementSideBar}</div>, sideBarRoot);
			},
        // 주의: 컴포넌트에 있는 실제 버그로 인해 발생한 예외를
        // 놓치지 않고 처리하기 위해서는
        // catch() 블록보다는 여기서 에러를 다뤄주는 게 중요합니다.
        (error) => {
         	} 	
      	)
	
	//ReactDOM.render(<Room title="abcdefghijklmnopqrstuvwxyz" adminName="aa"></Room>, root);
	
	
</script>
</body>
</html>