import React, { Component } from "react";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { doLogin } from "./login/loginAction";
import { TextField, Button, Box } from "@material-ui/core/";
import { Alert } from "@material-ui/lab";

class Login extends Component {
  constructor(props) {
    super(props);
    this.state = {
      credentials: {
        username: "",
        password: ""
      },
      isAuthenticated: false,
      token: "",
      errorMsg: ""
    };
  }

  login = async event => {
    event.preventDefault();
    let myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    let requestOptions = {
      method: "POST",
      headers: myHeaders,
      body: JSON.stringify(this.state.credentials)
    };

    this.props.doLogin(requestOptions);
  };

  handleInputChange = e =>
    this.setState({
      credentials: {
        ...this.state.credentials,
        [e.target.name]: e.target.value
      }
    });

  render() {
    return (
      <React.Fragment>
        <form className="container" onSubmit={e => this.login(e)}>
          <section className="mt-5 col-md-4 col-sm-10">
            <h4>Secured Login</h4>
            <small className="form-text text-muted">
              <i>Warning: only authorized users are allowed to proceed.</i>
              <hr />
            </small>

            <Box
              component="span"
              visibility={
                this.props.errorMsg !== undefined &&
                this.props.errorMsg.length > 0
                  ? "visible"
                  : "hidden"
              }
            >
              <br />
              <Alert severity="error">{this.props.errorMsg}</Alert>
            </Box>

            <div className="form-group">
              <TextField
                name="username"
                id="username"
                label="Username"
                type="text"
                value={this.state.credentials.username}
                onChange={this.handleInputChange}
              />
            </div>
            <div className="form-group">
              <TextField
                id="password"
                label="Password"
                name="password"
                type="password"
                autoComplete="current-password"
                value={this.state.credentials.password}
                onChange={this.handleInputChange}
              />
            </div>
            <br />
            <Button type="submit" variant="contained" color="primary">
              Login
            </Button>
          </section>
        </form>
      </React.Fragment>
    );
  }
}

Login.propTypes = {
  doLogin: PropTypes.func.isRequired,
  isAuthenticated: PropTypes.bool.isRequired,
  token: PropTypes.string.isRequired
};

const mapStateToProps = state => ({
  isAuthenticated: state.authReducer.isAuthenticated,
  token: state.authReducer.token,
  errorMsg: state.authReducer.errorMsg
});

export default connect(mapStateToProps, { doLogin })(Login);
