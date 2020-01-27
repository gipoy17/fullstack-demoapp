import React, { Component } from "react";
import MaterialTable from "material-table";
import { connect } from "react-redux";
import { getAuthReducer } from "./../localStorage";

class UserList extends Component {
  constructor(props) {
    const { token, isAuthenticated } = getAuthReducer();

    super(props);
    this.state = {
      error: null,
      isLoaded: false,
      data: [],
      columns: [
        { title: "First Name", field: "firstName" },
        { title: "Last Name", field: "lastName" },
        { title: "Username", field: "username" },
        { title: "Role", field: "role" },
        { title: "Password", field: "password" }
      ],
      isAuthenticated: isAuthenticated,
      token: token,
      errorMsg: ""
    };
  }

  retrieveUsers = async () => {
    await fetch("http://localhost:8080/user/all", {
      method: "GET",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
        Authorization: "Bearer " + this.state.token
      }
    })
      .then(res => res.json())
      .then(
        result => {
          if (result.status === 403) {
            //TODO: proper redirection
            window.location = "/user/create";
          } else {
            this.setState({
              isLoaded: true,
              data: result.data.users
            });
          }
        },
        error => {
          this.setState({
            isLoaded: false,
            error
          });
        }
      );
  };

  componentDidMount() {
    this.retrieveUsers();
  }

  createUser = async (data, method, url) => {
    const { firstName, lastName, username, password, role } = data;

    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    myHeaders.append("Authorization", "Bearer " + this.props.token);
    var requestOptions = {
      method: method,
      headers: myHeaders,
      body: JSON.stringify({
        firstName: firstName,
        lastName: lastName,
        username: username,
        password: password,
        role: role
      })
    };

    await fetch(url, requestOptions)
      .then(response => response.text())
      .catch(error => (window.location = "/user/create"));
  };

  render() {
    const { error, isLoaded, data, columns } = this.state;
    if (error) {
      return <div>Error: {error.message}</div>;
    } else if (!isLoaded) {
      return <div>Loading...</div>;
    } else {
      return (
        <MaterialTable
          title="Users"
          columns={columns}
          data={data}
          editable={{
            onRowAdd: newData =>
              new Promise(resolve => {
                this.createUser(
                  newData,
                  "POST",
                  "http://localhost:8080/user/create"
                );
                window.location = "/user/all";
              }),
            onRowUpdate: (newData, oldData) =>
              new Promise(resolve => {
                this.createUser(
                  newData,
                  "POST",
                  "http://localhost:8080/user/" + oldData.id
                );
                window.location = "/user/all";
              }),
            onRowDelete: oldData =>
              new Promise(resolve => {
                this.createUser(
                  oldData,
                  "POST",
                  "http://localhost:8080/user/delete/" + oldData.id
                );
                window.location = "/user/all";
              })
          }}
        />
      );
    }
  }
}

const mapStateToProps = state => ({
  isAuthenticated: state.authReducer.isAuthenticated,
  token: state.authReducer.token,
  errorMsg: state.authReducer.errorMsg
});
export default connect(mapStateToProps, {})(UserList);
