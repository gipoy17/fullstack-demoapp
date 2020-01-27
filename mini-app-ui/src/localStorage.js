export const loadState = () => {
  try {
    const serializedState = localStorage.getItem("state");
    if (serializedState == null) {
      return undefined;
    }
    return JSON.parse(serializedState);
  } catch (err) {
    return undefined;
  }
};

export const getAuthReducer = () => {
  if (loadState() === undefined) {
    return {
      isAuthenticated: false,
      token: ""
    };
  }
  return loadState().authReducer;
};

export const saveState = state => {
  try {
    localStorage.setItem("state", JSON.stringify(state));
  } catch (err) {
    //catch error
  }
};
