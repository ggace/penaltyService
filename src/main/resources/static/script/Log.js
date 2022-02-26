class Log extends React.Component {
	
	constructor(props) {
	    super(props)    
	}
	
	render() {
		return (
  		<div id={this.props.id} className="log" style={{display:"flex", height: "30px"}}>
  			<div style={{width: "50px", borderRight: "1px solid #8f8f8f", padding: "1px"}}>{this.props.logId}</div>	
  			<div style={{width: "70px", borderRight: "1px solid #8f8f8f", padding: "1px"}}>{this.props.user}</div>	
  			<div style={{display: "flex", 	flexGrow: "1", borderRight: "1px solid #8f8f8f", padding: "1px"}}>{this.props.content}</div>	
  			<div style={{width: "70px", borderRight: "1px solid #8f8f8f", padding: "1px"}}>{this.props.money}</div>
  			<div style={{width: "100px", padding: "1px"}}>{this.props.regDate.substring(0, 11)}</div>	
  		</div>
		);
	}
}