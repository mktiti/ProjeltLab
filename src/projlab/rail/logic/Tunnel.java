package projlab.rail.logic;

import projlab.rail.exception.IllegalMoveException;
import projlab.rail.exception.InactiveTunnelException;

import java.util.ArrayList;
import java.util.List;

/** Represents a tunnel opening */
public class Tunnel extends StaticEntity {
    /** Visible connection, connected to hidden */
    public StaticEntity visibleConnection;
    /** Hidden connection, connected to visible */
    public StaticEntity hiddenConnection;
    /** Whether the tunnel is activated or not */
    public boolean isActive;
    /** A list of all connections */
    private final ArrayList<StaticEntity> conns = new ArrayList<>(3);

    public Tunnel() {
        for (int i = 0; i < 2; i++) {
            conns.add(null);
        }
    }

    /** connects visible connection */
    public void connectVisible(StaticEntity visible) {
        conns.set(0, visible);
        visibleConnection = visible;
    }
    /** connects hidden connection */
    public void connectHidden(StaticEntity hidden) {
        conns.set(1, hidden);
        hiddenConnection = hidden;
    }

    @Override
    public List<StaticEntity> getConnections() {
        return conns;
    }

    @Override
    public StaticEntity next(StaticEntity previous) throws IllegalMoveException, InactiveTunnelException {
        if (!isActive) {
            throw new InactiveTunnelException(this);
        }

        if (previous == hiddenConnection) {
            return visibleConnection;
        } else if (previous == visibleConnection) {
            return hiddenConnection;
        }

        throw new IllegalMoveException(this, previous);
    }

    @Override
    public void connect(StaticEntity entity, ConnectionType connectionType) throws IllegalArgumentException {
        switch (connectionType) {
            case VISIBLE:
                connectVisible(entity);
                break;
            case INVISIBLE:
                connectHidden(entity);
                break;
            default:
                throw new IllegalArgumentException("Illegal connection type");
        }
    }
}