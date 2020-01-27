import { createStore, applyMiddleware } from "redux";
import thunk from "redux-thunk";
import rootReducer from "./reducers";
import { loadState, saveState } from "./localStorage";

const persistedState = loadState();
const middleware = [thunk];

const store = createStore(
  rootReducer,
  persistedState,
  applyMiddleware(...middleware)
);

store.subscribe(() => {
  saveState(store.getState());
});

export default store;
