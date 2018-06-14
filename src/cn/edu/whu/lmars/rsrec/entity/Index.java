package cn.edu.whu.lmars.rsrec.entity;

/**
 * 指标类，分为权重和得分，得分满分制是150
 * @author KingWang
 *
 */
public class Index{
	private double weight;
	private double score;
	
	public Index(double weight, double score) {
		super();
		this.weight = weight;
		this.score = score;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
}
