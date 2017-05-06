package projlab.rail.ui;

import projlab.rail.GameEngine;
import projlab.rail.logic.StaticEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class EntityPanel extends JPanel {

    private static final int PANEL_SIZE = 200;

    final StaticEntity entity;
    final GameEngine engine;

    private BufferedImage image = null;

    public EntityPanel(StaticEntity entity, GameEngine gameEngine) {
        this.entity = entity;
        this.engine = gameEngine;
        setSize(PANEL_SIZE, PANEL_SIZE);
        setPreferredSize(new Dimension(PANEL_SIZE, PANEL_SIZE));
    }

    public void update() {
        image = entity.image();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        if (image != null) {
            graphics.drawImage(image.getScaledInstance(PANEL_SIZE, PANEL_SIZE, 0), 0, 0, this);
        }
    }
}