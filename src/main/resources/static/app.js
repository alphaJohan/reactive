var stompClient = null;
var reactMessages = [];

var TableRow = React.createClass({
    render: function () {
        return <tr><td>{this.props.message}</td></tr>
    }
});

var MessageTable = React.createClass({
    getInitialState: function() {
        return {messages: this.props.messages};
    },
    componentDidMount: function() {
        var socket = new SockJS('/reactive-with-websocket');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            stompClient.subscribe('/topic/data', function (message) {
                reactMessages.push(message.body);
                this.setState({messages: reactMessages});
                if (message.body === 'finished') {
                    console.log('Closing stream');
                    disconnect();
                }
            }.bind(this));
        }.bind(this));
    },
    render: function() {
        var i = 0;
        var tableRows = this.state.messages.map(function(message) {
            i++;
            return (
                <TableRow key={i} message={message}/>
            )
        });
        return (
            <table className="table table-striped">
                <thead>
                <tr>
                    <th>Messages</th>
                </tr>
                </thead>
                <tbody>
                {tableRows}
                </tbody>
            </table>
        )
    }
});

ReactDOM.render(
    <MessageTable messages={reactMessages}/>,
    document.getElementById('root')
);

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    $("#messages").html("");
}

function connect() {
    $.ajax({url: "/openstream", type:'POST', success: function(result){
        setConnected(true);
    }});
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function generate() {
    $.ajax({url: "/generate", type:'POST', success: function(result){
        console.log('Data file generated: ' + result);
    }});
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#generate" ).click(function() { generate(); });

});


