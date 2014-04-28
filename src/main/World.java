package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import main.saving.IDManager;
import block.Block;

import particle.Particle;
import entity.Entity;
import entity.mob.Character;

public class World {

    public final int BLOCK_SIZE = 16;
    public final int GRAVITY = 1;
    public static final double VX_SLOWLY = 0.9;
    public boolean stepState = true;
    public static Input input;
    public static int k = 0;
    public static ArrayList<Island> islands;
    public ArrayList<Entity> entities;
    public ArrayList<Particle> particles;
    private Character character = null;

    public World() {
        clear();
//		createLevel();
        Game.pic = new Pictures();
    }

    public void clear() {
        islands = new ArrayList<Island>();
        entities = new ArrayList<Entity>();
        particles = new ArrayList<Particle>();
    }

    public void findCharacter() {
        for (Entity e : entities) {
            if (e instanceof Character) {
                character = (Character) e;
            }
        }
    }

    public Character getCharacter() {
        if (character == null) {
            findCharacter();
        }
        return character;
    }

    public void removeCharacters() {
        for (int q = 0; q < entities.size(); q++) {
            if (entities.get(q).getClass() == Character.class) {
                entities.remove(q);
                q--;
            }
        }
        character = null;
    }

    public void findGUI() {
        for (Entity e : entities) {
            if (e.getClass() == Character.class) {
                Game.setGUI(((Character) e).getGUI());
                return;
            }
        }
    }

    public void step() {
        for (int q = 0; q < entities.size(); q++) {
            Entity e = entities.get(q);
            if (e.isDeleted()) {
                e.onDeath();
                entities.remove(q);
                q--;
                continue;
            }
            e.tick();
        }
        for (int q = 0; q < entities.size(); q++) {
            for (int w = q + 1; w < entities.size(); w++) {
                if (!entities.get(q).isCollide(entities.get(w))) {
                    continue;
                }
                entities.get(q).interactOn(entities.get(w));
                entities.get(w).interactOn(entities.get(q));
            }
        }

//		---------------------------------- Particles ------------------------------

        for (int q = 0; q < particles.size(); q++) {
            Particle p = particles.get(q);
            if (p.isDeleted()) {
                p.onDeath();
                particles.remove(q);
                q--;
                continue;
            }
            p.tick();
        }
        for (int q = 0; q < particles.size(); q++) {
            for (int w = q + 1; w < particles.size(); w++) {
                particles.get(q).interactOn(particles.get(w));
                particles.get(w).interactOn(particles.get(q));
            }
        }

//        for(int q=0;q<islands.size();q++)
//        {
//        	Island island = islands.get(q);
//        	island.tick();
//        }
    }

    public boolean collideIslands(double x, double y) {
        boolean c = false;
        for (Island island : islands) {
            try {
                byte id = island.blocks[(int) ((x - island.getX()) / BLOCK_SIZE)][(int) ((y - island.getY()) / BLOCK_SIZE)];
                Block block = (Block) IDManager.getBlockClass(id).newInstance();
                if (block.getCollidable()) {
                    c = true;
                }
            } catch (Exception ex) {
            }
        }
        return c;
    }

    public Island getIsland(long x, long y) {
        for (Island island : islands) {
            if (x < island.getX()) {
                continue;
            }
            if (y < island.getY()) {
                continue;
            }
            if (x > island.getX() + island.blocks.length * BLOCK_SIZE) {
                continue;
            }
            if (y > island.getY() + island.blocks[0].length * BLOCK_SIZE) {
                continue;
            }
            return island;
        }
        return null;
    }

    public Entity getEntity(long x, long y) {
        for (Entity entity : entities) {
            if (entity.isCollide(x, y)) {
                return entity;
            }
        }
        return null;
    }

    public void deleteEntities(long x, long y) {
        for (int q = 0; q < entities.size(); q++) {
            if (entities.get(q).isCollide(x, y)) {
                entities.remove(q);
                q--;
            }
        }
    }

    public Entity getEntityByID(int id) {
        for (Entity e : entities) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

    public void draw(Graphics2D g) {

        g.setColor(Color.BLACK);
        for (Island i : islands) {
            i.draw(g);
        }
        for (Entity e : entities) {
            if (e.getCY() > 0) {
                g.rotate(Math.PI, e.getCX() - Game.x, e.getCY() - Game.y);
            }
            e.draw(g);
            if (e.getCY() > 0) {
                g.rotate(Math.PI, e.getCX() - Game.x, e.getCY() - Game.y);
            }
        }
        for (Particle p : particles) {
            p.draw(g);
        }

//		g.setColor(Color.ORANGE);
//		g.fillOval(-10 - Game.x, -10 - Game.y, 20, 20);
//		
//		g.setColor(Color.BLACK);
//		g.fillOval(-8 - Game.x, -8 - Game.y, 16, 16);
    }
}
