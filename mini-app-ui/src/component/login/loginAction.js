import { LOGIN } from "./type";

export const doLogin = requestOptions => async dispatch => {
  await fetch("http://localhost:8080/authenticate", requestOptions)
    .then(response => response.json())
    .then(result => {
      //hardcoded
      if (result.status === 500) {
        dispatch({
          type: LOGIN,
          isAuthenticated: false,
          token: "",
          errorMsg: result.message
        });
      } else {
        dispatch({
          type: LOGIN,
          isAuthenticated: true,
          token: result.data.jwt
        });
        //TODO: proper redirection
        window.location = "/user/all";
      }
    })
    .catch(error => {
      dispatch({
        type: LOGIN,
        errorMsg: "An error occured. Please try again later."
      });
    });
};
