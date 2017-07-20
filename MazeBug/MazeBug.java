package info.gridworld.maze;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import javax.swing.JOptionPane;

/**
 * A <code>MazeBug</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class MazeBug extends Bug {
	public Location next;
	public Location last;
	public boolean isEnd = false;
	//记录树节点的栈
	public Stack<ArrayList<Location>> crossLocation = new Stack<ArrayList<Location>>();
	public Integer stepCount = 0;
	boolean hasShown = false;//final message has been shown
	//进阶要求里面的四个方向的选择次数
	public int[] movedir = {0,0,0,0};
	/**
	 * Constructs a box bug that traces a square of a given side length
	 * 
	 * @param length
	 *            the side length
	 */
	public MazeBug() {
		setColor(Color.GREEN);
		last = new Location(0, 0);
	}

	/**
	 * Moves to the next location of the square.
	 */
	public void act() {
		//将起始节点入栈
		if (stepCount == 0) {
			ArrayList<Location> temp = new ArrayList<Location>();
			//进入节点的方向
			temp.add(getLocation());
			crossLocation.push(temp);
		}
		boolean willMove = canMove();
		if (isEnd == true) {
		//to show step count when reach the goal		
			if (hasShown == false) {
				String msg = stepCount.toString() + " steps";
				JOptionPane.showMessageDialog(null, msg);
				hasShown = true;
			}
		} else if (willMove) {
			move();
			//increase step count when move 
			stepCount++;
		}
		else{
			back();
			stepCount++;
		} 
	}

	/**
	 * Find all positions that can be move to.
	 * 
	 * @param loc
	 *            the location to detect.
	 * @return List of positions.
	 */
	public ArrayList<Location> getValid(Location loc) {
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return null;
		ArrayList<Location> valid = new ArrayList<Location>();
		//取当前栈顶节点
		ArrayList<Location> temp = crossLocation.peek();
		for (int i = 0; i < 4 ;i++ ) {
			//find new location hasn't been
			Location loc1 = loc.getAdjacentLocation(i * Location.RIGHT);
			//如果栈顶节点存在未访问的邻接节点，置为可访问的方向，入栈（加入方向树）
			if ((temp.contains(loc1)== false) && gr.isValid(loc1)) {
				
				Actor a = gr.get(loc1);
				if ((a == null) || ( a instanceof Flower)) {
					valid.add(loc1);
				}
				
				//如果栈顶节点是结束节点（红色石头），结束搜索
				if (a instanceof Rock && a.getColor().getRed() == Color.RED.getRed()) {
					isEnd = true;
					valid.add(loc1);
					moveTo(loc1);
				}
			}
		}
		return valid;
	}

	/**
	 * Tests whether this bug can move forward into a location that is empty or
	 * contains a flower.
	 * 
	 * @return true if this bug can move.
	 */
	public boolean canMove() {
		return !getValid(getLocation()).isEmpty();
	}
	/**
	 * Moves the bug forward, putting a flower into the location it previously
	 * occupied.
	 */
	public void move() {
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return;
		Location loc = getLocation();
		
		ArrayList<Location> possibleLocs = getValid(loc);
		int n = possibleLocs.size();
		//选择最大可能移动的方向
		int max = movedir[loc.getDirectionToward(possibleLocs.get(0))/Location.RIGHT];
		int tardirec = 0;
		for(int i = 0; i < n; i++){
			int temp = movedir[loc.getDirectionToward(possibleLocs.get(i))/Location.RIGHT];
			if(max < temp){
				max = temp;
				tardirec = i;
			}
		}
		next = possibleLocs.get(tardirec);
		//move to selected location
		if (gr.isValid(next)) {
			setDirection(getLocation().getDirectionToward(next));
			moveTo(next);
		} else
			removeSelfFromGrid();
		Flower flower = new Flower(getColor());
		flower.putSelfInGrid(gr, loc);
		
		//该方向的选择次数++
		movedir[getDirection()/90]++;
		//下一步要走的位置，含下一步路线的树压栈
		ArrayList<Location> temp = crossLocation.pop();
		temp.add(next);
		crossLocation.push(temp);
		
		//记录上一步的位置，含上一步路线的树压栈
		last = loc;
		
		temp = new ArrayList<Location>();
		temp.add(getLocation());
		temp.add(loc);
		crossLocation.push(temp);
	}
	
	//仿照move写的回溯函数
	public void back(){
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return;
		crossLocation.pop();
		//取上一次方向树，取节点设为next
		ArrayList<Location> temp = crossLocation.peek();
		next = (Location) temp.get(0);
		Location loc = getLocation();
		
		if (gr.isValid(next)) {
			setDirection(getLocation().getDirectionToward(next));
			moveTo(next);
		} else
			removeSelfFromGrid();
		Flower flower = new Flower(getColor());
		flower.putSelfInGrid(gr, loc);

		last = loc;
	}
}
