import React, { Component } from "react";
import { Route } from "react-router-dom";
import { connect } from "react-redux";
import {
  AppBar,
  Toolbar,
  Typography,
  Grid,
  List,
  ListItem,
  ListItemText,
  Paper
} from "@material-ui/core/";
const ListItemLink = props => {
  return <ListItem button component="a" {...props} />;
};
class HeaderTemplate extends Component {
  render() {
    return (
      <div>
        <Grid container spacing={2}>
          <Grid item xs={12}>
            <AppBar position="static">
              <Toolbar>
                <Typography variant="h6">Mini-App Demo</Typography>
              </Toolbar>
            </AppBar>
          </Grid>
          <Grid item xs={2}>
            <Paper className="paper">
              <List component="nav" aria-label="primary mailbox folders">
                {this.props.navLinks.map(link => {
                  return (
                    <ListItemLink key={link.path} href={link.path}>
                      <ListItemText primary={link.name} />
                    </ListItemLink>
                  );
                })}
                ;
              </List>
            </Paper>
          </Grid>
          <Grid item xs={9}>
            {this.props.navLinks.map(link => {
              return (
                <Route
                  key={link.path}
                  exact={link.exact}
                  path={link.path}
                  component={link.component}
                />
              );
            })}
          </Grid>
        </Grid>
      </div>
    );
  }
}

const mapStateToProps = state => ({
  isAuthenticated: state.authReducer.isAuthenticated,
  token: state.authReducer.token
});

export default connect(mapStateToProps, {})(HeaderTemplate);
