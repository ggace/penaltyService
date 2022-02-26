class Room extends React.Component {
	render() {
		
		const style = {
			width: "302px",
			height: "24vh",
			border: "1px solid gray",
			borderRadius: "15px",
			marginRight: "40px",
			marginBottom:"35px",
			display: "flex",
			alignItems:"flex-start",
			flexDirection: "column",
			boxShadow: "3px 3px 4px #5f5f5f"
		}
		return (
  		<div className="room" style={style}>
  			<RoomHeader title={this.props.title} adminName={this.props.adminName} roomId={this.props.roomId}></RoomHeader>
  			<div className="flex-grow"></div>
  			<RoomFooter></RoomFooter>
  		</div>
		);
	}
}

class RoomHeader extends React.Component{
	constructor(props) {
	    super(props);
	
	    this.state = {
	      	style : {
				width: "300px",
				height: "100px",
				backgroundColor: "#99ceff",
				borderTopLeftRadius: "15px",
				borderTopRightRadius: "15px",
				display: "flex",
				alignItems: "center",
				justifyContent: "center"
			}
	    };
	  }
	render() {
		let aHref = "../client/detail?id=" + this.props.roomId
		return (
  		<div className="roomHeader" style={this.state.style}>
  			
  			<span style={{fontSize: "1.375rem", width: "268px", height: "48px", textAlign:"left", display: "flex"}}>
  				<div className="flex" style={{alignItems: "center", width: "238px"}}>
  					<a href={aHref} className="hover:underline" style={{color: "black", display: "inline-block", textOverflow: "ellipsis", overflow: "hidden", whiteSpace: "nowrap", width: "100%"}}>
  						{this.props.title}
  						<div style={{fontSize: "0.8125rem"}}>{this.props.adminName}</div>
  					</a>
  						
  					
  				</div>
  				
  				<button className="btn btn-ghost btn-circle m-0 p-0 btn-sm">
		  				<svg style={{display: "inline"}} focusable="false" width="24" height="24" viewBox="0 0 24 24" className=" NMm5M">
		  					<path d="M12 8c1.1 0 2-.9 2-2s-.9-2-2-2-2 .9-2 2 .9 2 2 2zm0 2c-1.1 0-2 .9-2 2s.9 2 2 2 2-.9 2-2-.9-2-2-2zm0 6c-1.1 0-2 .9-2 2s.9 2 2 2 2-.9 2-2-.9-2-2-2z"></path>
		  				</svg>
	  				</button>
  			</span>
    		 
  		</div>
		);
	}
}

class RoomFooter extends React.Component{
	constructor(props) {
	    super(props);
	
	    this.state = {
	      	style: {
				width: "300px",
				height: "57px",
				padding: "4px",
				borderTop: "1px solid #c0c0c0"
				
			}
	    };
	  }
	  
	render() {
		return <div style={this.state.style}></div>
	}
}