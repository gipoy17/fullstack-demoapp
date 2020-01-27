import Login from "./component/login";
import UserList from "./component/listusers";
import NewUser from "./component/newuser";

const routes = {
  navLinks: [
    {
      path: "/login",
      name: "Login",
      visible: true,
      requiresAuth: false,
      exact: false,
      component: Login,
      data: {}
    },
    {
      path: "/user/create",
      name: "Register",
      visible: true,
      requiresAuth: false,
      exact: false,
      component: NewUser,
      data: {}
    },
    {
      path: "/user/all",
      name: "Users",
      visible: true,
      requiresAuth: true,
      exact: false,
      component: UserList,
      data: {}
    }
  ]
};

export { routes };
