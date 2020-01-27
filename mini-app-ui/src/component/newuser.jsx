import React, { Component } from "react";
import { makeStyles } from "@material-ui/core/styles";
import { TextField, Button } from "@material-ui/core";

class NewUser extends Component {
  constructor(props) {
    super(props);
    this.state = {
      //TODO: create pojo class
      user: {
        firstName: "",
        lastName: "",
        username: "",
        password: "",
        role: ""
      }
    };
  }

  createUser = async event => {
    event.preventDefault();
    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    var requestOptions = {
      method: "POST",
      headers: myHeaders,
      body: JSON.stringify(this.state.user)
    };

    //hard-coded URL
    await fetch("http://localhost:8080/user/create", requestOptions)
      .then(response => response.text())
      .then(result => (window.location = "/login")) //TODO: hardcoded redirect
      .catch(error => console.log("error", error));
  };

  handleInputChange = e =>
    this.setState({
      user: { ...this.state.user, [e.target.name]: e.target.value }
    });

  render() {
    const classes = makeStyles(theme => ({
      root: {
        "& .MuiTextField-root": {
          margin: theme.spacing(2),
          width: 400
        }
      }
    }));

    return (
      <React.Fragment>
        <form
          className={classes.root}
          noValidate
          autoComplete="off"
          onSubmit={event => this.createUser(event)}
        >
          <section className="mt-5 col-md-6 col-sm-8 col-xs-10">
            <h1>
              <i className="fas fa-user"></i> Create User
            </h1>
            <hr />
            <div className="form-group">
              <TextField
                type="text"
                className="form-control"
                id="firstName"
                name="firstName"
                aria-describedby="firstNameHelp"
                label="First Name"
                value={this.state.user.firstName}
                onChange={this.handleInputChange}
              />
            </div>
            <div className="form-group">
              <TextField
                type="text"
                className="form-control"
                id="lastName"
                name="lastName"
                aria-describedby="lastNameHelp"
                label="Last Name"
                value={this.state.user.lastName}
                onChange={this.handleInputChange}
              />
            </div>
            <div className="form-group">
              <TextField
                type="text"
                className="form-control"
                id="username"
                name="username"
                aria-describedby="usernameHelp"
                label="Username"
                value={this.state.user.username}
                onChange={this.handleInputChange}
              />
            </div>
            <div className="form-group">
              <TextField
                type="password"
                className="form-control"
                id="password"
                name="password"
                label="Password"
                value={this.state.user.password}
                onChange={this.handleInputChange}
              />
            </div>
            <div className="form-group">
              <TextField
                type="text"
                className="form-control"
                id="role"
                name="role"
                aria-describedby="roleHelp"
                label="Role"
                value={this.state.user.role}
                onChange={this.handleInputChange}
              />
            </div>

            <br />
            <br />
            <Button type="submit" variant="contained" color="primary">
              Submit
            </Button>
          </section>
        </form>
      </React.Fragment>
    );
  }
}

export default NewUser;
