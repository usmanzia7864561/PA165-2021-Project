import { connect } from "react-redux";
import { Switch, Route } from "react-router-dom";
import Auth from "./pages/auth";
import Main, { ProtectedRoute } from "./pages/home/index";
import { IAppState } from "./store/rootReducer";

export interface IAppProps {
    isLoggedIn: boolean;
}

const App: React.FC<IAppProps> = ({ isLoggedIn }) => (
    <div>
        <Switch>
            <Route path="/auth" component={Auth} />
            <ProtectedRoute path="/" component={Main} isLoggedIn={isLoggedIn} />
        </Switch>
    </div>
);

const mapStateToProps = ({ auth }: IAppState) => ({
    isLoggedIn: auth.isLoggedIn,
});

export default connect(mapStateToProps)(App);
