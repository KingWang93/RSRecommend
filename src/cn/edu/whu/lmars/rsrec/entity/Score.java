package cn.edu.whu.lmars.rsrec.entity;

import java.lang.reflect.Field;

/**
 * 各项得分
 * @author KingWang
 *
 */
public class Score {
	private Index spatialResolutionIndex;
	private Index timeResolutionIndex;
	private Index spectrumResolutionIndex;
	private Index radiationResolutionIndex;
	private Index widthIndex;
	private Index snrIndex;
	private Index angleIndex;
	private Index waveBandIndex;
	private Index scaleIndex;
	private Index typeIndex;
	private Index modeIndex;
	private Index poleDemandIndex;
	private Index poleMethodIndex;
	private Index spatialIndex;
	private Index timeIndex;
	
	private double totalScore;
	
	public Index getSpatialResolutionIndex() {
		return spatialResolutionIndex;
	}
	public void setSpatialResolutionIndex(Index spatialResolutionIndex) {
		this.spatialResolutionIndex = spatialResolutionIndex;
	}
	public Index getTimeResolutionIndex() {
		return timeResolutionIndex;
	}
	public void setTimeResolutionIndex(Index timeResolutionIndex) {
		this.timeResolutionIndex = timeResolutionIndex;
	}
	public Index getSpectrumResolutionIndex() {
		return spectrumResolutionIndex;
	}
	public void setSpectrumResolutionIndex(Index spectrumResolutionIndex) {
		this.spectrumResolutionIndex = spectrumResolutionIndex;
	}
	public Index getRadiationResolutionIndex() {
		return radiationResolutionIndex;
	}
	public void setRadiationResolutionIndex(Index radiationResolutionIndex) {
		this.radiationResolutionIndex = radiationResolutionIndex;
	}
	public Index getWidthIndex() {
		return widthIndex;
	}
	public void setWidthIndex(Index widthIndex) {
		this.widthIndex = widthIndex;
	}
	public Index getSnrIndex() {
		return snrIndex;
	}
	public void setSnrIndex(Index snrIndex) {
		this.snrIndex = snrIndex;
	}
	public Index getAngleIndex() {
		return angleIndex;
	}
	public void setAngleIndex(Index angleIndex) {
		this.angleIndex = angleIndex;
	}
	public Index getWaveBandIndex() {
		return waveBandIndex;
	}
	public void setWaveBandIndex(Index waveBandIndex) {
		this.waveBandIndex = waveBandIndex;
	}
	public Index getScaleIndex() {
		return scaleIndex;
	}
	public void setScaleIndex(Index scaleIndex) {
		this.scaleIndex = scaleIndex;
	}
	public Index getTypeIndex() {
		return typeIndex;
	}
	public void setTypeIndex(Index typeIndex) {
		this.typeIndex = typeIndex;
	}
	public Index getModeIndex() {
		return modeIndex;
	}
	public void setModeIndex(Index modeIndex) {
		this.modeIndex = modeIndex;
	}
	public Index getPoleDemandIndex() {
		return poleDemandIndex;
	}
	public void setPoleDemandIndex(Index poleDemandIndex) {
		this.poleDemandIndex = poleDemandIndex;
	}
	public Index getPoleMethodIndex() {
		return poleMethodIndex;
	}
	public void setPoleMethodIndex(Index poleMethodIndex) {
		this.poleMethodIndex = poleMethodIndex;
	}
	public Index getSpatialIndex() {
		return spatialIndex;
	}
	public void setSpatialIndex(Index spatialIndex) {
		this.spatialIndex = spatialIndex;
	}
	public Index getTimeIndex() {
		return timeIndex;
	}
	public void setTimeIndex(Index timeIndex) {
		this.timeIndex = timeIndex;
	}
	public double getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(double totalScore) {
		this.totalScore = totalScore;
	}
	/**
	 * 利用反射计算所有的Index类的变量，进行得分求和
	 * @return
	 */
	public double evaluate(){
		Field[] fields = this.getClass().getDeclaredFields();
		for(Field f:fields){
			if(f.getType()==Index.class){
				try {
					totalScore += ((Index)f.get(this)).getScore()*((Index)f.get(this)).getWeight();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return totalScore;
	}
	public static void main(String[] args) {
		Score score = new Score();
		score.setAngleIndex(new Index(5,1.0));
		score.setModeIndex(new Index(5,2.0));
		score.setPoleDemandIndex(new Index(5,1.0));
		score.setPoleMethodIndex(new Index(5,1.0));
		score.setRadiationResolutionIndex(new Index(5,1.0));
		score.setScaleIndex(new Index(5,1.0));
		score.setSnrIndex(new Index(5,1.0));
		score.setSpatialIndex(new Index(5,1.0));
		score.setSpatialResolutionIndex(new Index(5,1.0));
		score.setSpectrumResolutionIndex(new Index(5,1.0));
		score.setTimeIndex(new Index(5,1.0));
		score.setTimeResolutionIndex(new Index(5,1.0));
		score.setTypeIndex(new Index(5,1.0));
		score.setWaveBandIndex(new Index(5,1.0));
		score.setWidthIndex(new Index(5,1.0));
		System.out.println(score.evaluate());
	}
	
}
