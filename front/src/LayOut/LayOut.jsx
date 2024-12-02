import React from "react";

import { Outlet, RouterProvider, createBrowserRouter } from "react-router-dom";

//pages.............
import Login from "../pages/login/Login";
import Signup from "../pages/signup/Signup";
import Home from "../pages/home/Home";
import Profile from "../pages/profile/Profile";
import Chatbox from "../pages/chatbox/Chatbox";
import Notification from "../pages/notification/Notification";

// Components.............
import Nav from "../components/nav/Nav";
import LeftBar from "../components/leftbar/LeftBar";
import RightBar from "../components/rightbar/RightBar";
import Notify from "../components/notify/notify";
import PublicRoute from '../components/security/PublicRoute';
import ProtectedRoute from '../components/security/ProtectedRoute';
import { UserProvider } from '../context/UserContext';
import Test from '../pages/test/TestComponent';

export default function Layout(){

    // Feed.........
    const Feed =()=>{
        return (
            <>
            <Nav />
            <main>
                <LeftBar />
                <div className="container">
                    <Outlet />
                </div>
                <RightBar />
            </main>
            </>
        )
    }

    // Router..........
    const router = createBrowserRouter([
        {
            path: "/",
            element: (
                <UserProvider>
                    <ProtectedRoute>
                        <Feed />
                    </ProtectedRoute>
                </UserProvider>
            ),
            children: [
                {
                    path: "/",
                    element: <Home />,
                },
                {
                    path: "/profile/:profileId",
                    element: <Profile />,
                },
                {
                    path: "/chatbox/:receiverId",
                    element: <Chatbox />,
                },
                {
                    path:"/notification",
                    element: <Notification />,
                }
            ]
        },
        {
            path: "/login",
            element: (
                <PublicRoute>
                    <Login />
                </PublicRoute>
            )
        },
        {
            path: "/signup",
            element: (
                <PublicRoute>
                    <Signup />
                </PublicRoute>
            )
        },
        {
            path: "/test",
            element: (
                <Test />
            )
        },
    ]);
    return (
        <>
            <RouterProvider router={router} />
        </>
    )
}