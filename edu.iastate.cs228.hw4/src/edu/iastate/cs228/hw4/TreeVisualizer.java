package edu.iastate.cs228.hw4;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class TreeVisualizer {
	private MsgTree rootNode;
	private int TreeDepth;

	private VisualNode VSNodeTree;
	private ArrayList<VisualNode> TreeAsArray = new ArrayList<VisualNode>();

// Config for visual nodes
	private int NodeWidth = 50;
	private int NodeHeight = 40;
	private int NodeSpace = 40;

	private int wWidth = 1800;
	private int wHeight = 1000;

	private int xoffset = 0;
	private int yoffset = 0;

	private int scale = 1;

	/**
	 *
	 * @author Daniel Mitchell This class is very simmilar to MsgTree but it has
	 *         extra methods needed to visually draw the tree.
	 *
	 */

	private class VisualNode {

		private int offset = 0;
		private int row = 1;
//private int depth =1;

		private int x, y;

		public String data;
		public VisualNode Parent, Lch, Rch;

		/**
		 *
		 * @param msg The letter the node holds
		 * @param off This tells if its the left or right child
		 * @param row What row of the tree is it in
		 */
		public VisualNode(String msg, int off, int row) {
			data = msg;
			offset = off;
			this.row = row;
			if (this.Parent != null) {
				this.x = Parent.x + ((NodeSpace * Math.max(1, (TreeDepth / this.row)) * offset));
				this.y = Parent.y + NodeHeight / 2;
			} else {
				this.x = wWidth / 2;
				this.y = 100;
			}
		}

		/**
		 *
		 * @param msg
		 * @param off
		 * @param parent the parent to be assigned to the new node.
		 * @param row
		 */
		public VisualNode(String msg, int off, VisualNode parent, int row) {
			data = msg;
			offset = off;
			this.row = row;
			this.Parent = parent;

//Do some math to kinda determine where to draw this node.
			if (this.Parent != null) {
				this.x = Parent.x + ((NodeSpace * Math.max(1, (TreeDepth / this.row)) * offset));
				this.y = Parent.y + NodeHeight + 10;

				if (this.data != "Parent") {
					this.x = Parent.x + (NodeSpace * offset);
				}
			} else {
				this.x = wWidth / 2;
				this.y = 100;
			}
		}

		public void paint(Graphics g) {
//Save color from before
//Color tCol = g.getColor();

//Draw this Node

//Nodefill
			g.setColor(Color.gray);
			g.fillRoundRect(((this.x - (NodeWidth / 2)) + xoffset) * scale, (this.y + yoffset) * scale,
					NodeWidth * scale, NodeHeight * scale, 5, 5);

//NodeBorders
			g.setColor(Color.getHSBColor(198, 7, 62));
			g.drawRoundRect(((this.x - (NodeWidth / 2)) + xoffset) * scale, (this.y + yoffset) * scale,
					NodeWidth * scale, NodeHeight * scale, 5, 5);

//Text
			g.setColor(Color.BLUE);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 14));
			g.drawString(data, ((this.x - NodeWidth / 2) + xoffset) * scale,
					((this.y + NodeHeight / 2) + yoffset) * scale);

//connectors
			if (this.Parent != null) {
				int parentx = Parent.x;
				int parenty = Parent.y + NodeHeight;
				g.setColor(Color.MAGENTA);
				g.drawLine((parentx + xoffset) * scale, (parenty + yoffset) * scale, (this.x + xoffset) * scale,
						(this.y + yoffset) * scale);
			}

			// return color to what it was
			// g.setColor(tCol);
		}
	}

	/*
	 * Private class that handles the Java AWT graphics, the stuff of nightmares,
	 * truly.
	 */
	private class MyFrame extends Frame implements MouseMotionListener {

		private Point click;
		private VisualNode RootTree;

		public MyFrame(VisualNode root) {
			RootTree = root;

			setVisible(true);
			setTitle("NodeViewer");
			setSize(wWidth, wHeight);

//add event listeners for keys and mouse
			addMouseMotionListener(this);

			this.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					click = e.getLocationOnScreen();
					// repaint();
				}
			});

			this.addKeyListener(new KeyListener() {

				@Override
				public void keyTyped(KeyEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void keyPressed(KeyEvent e) {
//System.out.println(e.getKeyCode());
					if (e.getKeyCode() == 45) {
						if (scale > 1) {
							scale += -1;
							repaint();

						}
					} else if (e.getKeyCode() == 61) {
						scale += 1;
						repaint();
					}

				}

				@Override
				public void keyReleased(KeyEvent e) {
// TODO Auto-generated method stub

				}

			});
			addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
		}

		// main paint
		public void paint(Graphics g) {
			super.paint(g);

//Back
			g.setColor(Color.DARK_GRAY);
			g.fillRect(0, 0, wWidth, wHeight);
			for (VisualNode i : TreeAsArray) {
				i.paint(g);
			}
		}

		public void repaint() {
			super.repaint();
			// this.paint(this.getGraphics());
		}

		@Override
		public void mouseDragged(MouseEvent e) {
//System.out.println("MOUSEDRAG");
			xoffset += e.getX() - click.x;
			yoffset += e.getY() - click.y;
			click = e.getPoint();
			repaint();

		}

		@Override
		public void mouseMoved(MouseEvent e) {
// TODO Auto-generated method stub

		}
	}

	/*
	 * Node to use as the root of the tree.
	 */
	public TreeVisualizer(MsgTree node) {
		rootNode = node;
		TreeDepth = findDepth();
		buildVis();
	}

	/*
	 * Builds the tree of visual nodes
	 */
	public void buildVis() {

		VSNodeTree = new VisualNode(rootNode.payloadChar + "Root", 0, 1);
		recVisBuilder(rootNode, 0, VSNodeTree);
		collDetect();

	}

	/*
	 * Half baked collision detection so that we can nudge nodes around if they get
	 * to intimate.
	 */
	private void collDetect() {
		for (VisualNode n : TreeAsArray) {
			for (VisualNode j : TreeAsArray) {
				if (n.row == j.row) {
					if (n.data != j.data) {
						if (Math.abs(n.x - j.x) < NodeWidth - 5) {
							n.x = (n.x - (((NodeSpace - 10) / 2) * n.offset));
							j.x = (j.x - (((NodeSpace - 10) / 2) * j.offset));

						}
					}
				}
			}
		}
	}

	public int findDepth() {
		int maxDepth = recDepth(rootNode, 0);
		TreeDepth = maxDepth;

		return maxDepth;
	}

	public int findDepth(MsgTree node) {
		int maxDepth = recDepth(node, 0);

		return maxDepth;
	}

	/*
	 * Recursive way to find the depth of the tree
	 */
	private int recDepth(MsgTree node, int depth) {
		int left = depth, right = depth;

		// System.out.println("RecDepth at" + depth);

		if (node.left != null) {
			left = recDepth(node.left, depth + 1);
		}
		if (node.right != null) {
			right = recDepth(node.right, depth + 1);
		}

		if (right > left) {
			return right;
		} else {
			return left;
		}
	}

	/*
	 * Recursively build a VN tree from MSGtree.
	 */
	private void recVisBuilder(MsgTree node, int depth, VisualNode VN) {

		TreeAsArray.add(VN);

		if (node.left != null) {
			if ((int) node.left.payloadChar == 0) {
				VN.Lch = new VisualNode("Parent", -1, VN, depth + 1);
			} else {
				VN.Lch = new VisualNode("Data: " + node.left.payloadChar, -1, VN, depth + 1);
			}
			// VN.Lch.Parent = VN;

			recVisBuilder(node.left, depth + 1, VN.Lch);

		}
		if (node.right != null) {
			if ((int) node.right.payloadChar == 0) {
				VN.Rch = new VisualNode("Parent", 1, VN, depth + 1);
			} else {
				VN.Rch = new VisualNode("Data: " + node.right.payloadChar, 1, VN, depth + 1);
			}
			// VN.Rch.Parent = VN;

			recVisBuilder(node.right, depth + 1, VN.Rch);

		}

	}

	/*
	 * Open a window and draw tree
	 */
	public void Display() {
		Frame MF = new MyFrame(VSNodeTree);

	}

}
