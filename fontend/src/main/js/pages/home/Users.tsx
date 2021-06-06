import * as React from "react";
import { Button } from "react-bootstrap";
import { connect } from "react-redux";
import { useHistory } from "react-router";
import { Link } from "react-router-dom";
import { toast } from "react-toastify";

import TableWrapper from "../../components/TableWrapper";
import userService from "../../service/user";
import { IUser } from "../../store/auth";
import { IAppState } from "../../store/rootReducer";

export interface UsersProps {
    user?: IUser;
}

const Users: React.FC<UsersProps> = ({ user }) => {
    const [users, setUsers] = React.useState<IUser[]>([]);
    const { replace, push } = useHistory();

    React.useLayoutEffect(() => {
        if (user?.type === "TENNIS_USER") {
            replace("/");
        }
    }, []);

    React.useEffect(() => {
        userService.all<IUser[]>().then((response) => {
            setUsers(response.data);
        });
    }, []);

    const deleteUser = (userId: number) => {
        userService.delete(userId)
    };
    return (
        <TableWrapper title="Users">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                {users.map((user) => (
                    <tr key={user.id}>
                        <td>{user.id}</td>
                        <td>{user.name}</td>
                        <td>{user.email}</td>
                        <td className="text-right">
                            <Button
                                size="sm"
                                className="mr-1"
                                to={`/user/update/${user.id}`}
                                as={Link}
                                variant="primary"
                            >
                                Edit
                            </Button>
                            <Button size="sm" variant="danger" onClick={() => deleteUser(user.id)}>
                                Delete
                            </Button>
                        </td>
                    </tr>
                ))}
            </tbody>
        </TableWrapper>
    );
};

const mapStateToProps = ({ auth }: IAppState) => ({
    user: auth.user,
});

export default connect(mapStateToProps)(Users);
