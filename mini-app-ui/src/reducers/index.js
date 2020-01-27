import { combineReducers } from "redux";
import authReducer from "../component/login/authReducer";
const reducers = combineReducers({
  authReducer: authReducer
});

export default reducers;
