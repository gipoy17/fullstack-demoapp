import { LOGIN } from "./type";

const initialState = {
  isAuthenticated: false,
  token: "",
  errorMsg: ""
};

export default function(state = initialState, action) {
  switch (action.type) {
    case LOGIN:
      return {
        ...state,
        isAuthenticated: action.isAuthenticated,
        token: action.token,
        errorMsg: action.errorMsg
      };
    default:
      return state;
  }
}
