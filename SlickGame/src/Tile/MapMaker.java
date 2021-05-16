package Tile;

import java.util.Random;

public class MapMaker {

	private int width;
	private int height;

	private int[][] list;

	private Random ran;

	private int it;
	private int fs;
	private double randon = 49;

	private boolean fillholes = false;

	public MapMaker(int width, int height, Random ran, int it, int fs, double randon, boolean fillholes) {
		super();
		this.width = width;
		this.height = height;
		this.ran = ran;
		this.it = it;
		this.fs = fs;
		this.randon = randon;
		this.fillholes = fillholes;
	}

	public MapMaker(int width, int height) {
		this(width, height, new Random(), 4, 0, 50, true);
	}

	public MapMaker(int width, int height, String seed, int it, int fs, double randon, boolean fillholes) {
		super();
		this.width = width;
		this.height = height;
		this.it = it;
		this.fs = fs;
		this.randon = randon;
		this.fillholes = fillholes;
		ran = new Random();
	}

	public void creat(String seed) {
		ran = new Random(seed.hashCode());
		creat();
	}

	public void creat(long seed) {
		ran = new Random(seed);
		creat();
	}

	public void creat() {
		ran = new Random();
		list = new int[width][height];

		for (int i = 0; i < list.length; i++) {
			for (int j = 0; j < list[0].length; j++) {
				if (ran.nextDouble() < randon / 100.0) {
					list[i][j] = 1;
				} else {
					list[i][j] = 0;
				}
			}
		}

		for (int i = 0; i < it; i++) {
			itaration();
		}

		for (int i = 0; i < fs; i++) {
			// fillspikes();
		}
		if (fillholes) {
			fillholes();
		}

	}

	public void itaration() {
		int[][] templist = new int[width][height];

		int number = 4;

		for (int i = 0; i < list.length; i++) {
			for (int j = 0; j < list[0].length; j++) {
				int n = 0;
				// System.out.println("i: " + i + ", j: " + j);
				for (int i2 = i - 1; i2 <= i + 1; i2++) {
					for (int j2 = j - 1; j2 <= j + 1; j2++) {

						if (!(i2 == i && j2 == j)) {
							// System.out.println("i2: " + i2 + ", j2: " + j2);
							if (i2 < 0 || i2 > list.length - 1 || j2 < 0 || j2 > list[0].length - 1) {
								n++;
							} else {
								if (list[i2][j2] == 1) {
									n++;
								}
							}
						}
					}
				}
				if (n > number) {
					templist[i][j] = 1;
				} else if (n < number) {
					templist[i][j] = 0;
				} else {
					templist[i][j] = list[i][j];
				}
			}
		}
		list = templist;

	}

	public void fillspikes() {

		int[][] templist = new int[width][height];

		for (int i = 0; i < list.length; i++) {
			for (int j = 0; j < list[0].length; j++) {

				if (list[i][j] == 1) {
					templist[i][j] = 1;
				} else {

					templist[i][j] = 0;
					if (i - 1 < 0) {
						if (i + 1 > list.length - 1) {
							templist[i][j] = 1;
						} else {
							if (list[i + 1][j] == 1) {
								templist[i][j] = 1;
							}
						}
					} else if (list[i - 1][j] == 1) {
						if (i + 1 > list.length - 1) {
							templist[i][j] = 1;
						} else {
							if (list[i + 1][j] == 1) {
								templist[i][j] = 1;
							}
						}
					}

					if (j - 1 < 0) {
						if (j + 1 > list[0].length - 1) {
							templist[i][j] = 1;
						} else {
							if (list[i][j + 1] == 1) {
								templist[i][j] = 1;
							}
						}
					} else if (list[i][j - 1] == 1) {
						if (j + 1 > list.length - 1) {
							templist[i][j] = 1;
						} else {
							if (list[i][j + 1] == 1) {
								templist[i][j] = 1;
							}
						}
					}

				}

			}
		}

		list = templist;
	}

	public void fillholes() {

		int[] areas = new int[100000];
		int free = 1;

		int[][] templist = new int[width][height];

		for (int i = 0; i < list.length; i++) {
			for (int j = 0; j < list[0].length; j++) {

				if (i == 0 || i == list.length - 1 || j == 0 || j == list[0].length - 1) {
					list[i][j] = 1;
				}

			}
		}

		for (int i = 0; i < list.length; i++) {
			for (int j = 0; j < list[0].length; j++) {

				if (list[i][j] == 0) {
					if (templist[i][j] == 0) {

						int pointInXY = 0;

						int[] x = new int[list.length * list[0].length];
						int[] y = new int[list.length * list[0].length];

						x[0] = i;
						y[0] = j;

						while (pointInXY >= 0) {

							areas[free]++;
							templist[x[pointInXY]][y[pointInXY]] = free;

							int currentx = x[pointInXY];
							int currenty = y[pointInXY];
							pointInXY--;

							if (list[currentx - 1][currenty] == 0 && templist[currentx - 1][currenty] == 0) {
								pointInXY++;
								x[pointInXY] = currentx - 1;
								y[pointInXY] = currenty;
							}

							if (list[currentx + 1][currenty] == 0 && templist[currentx + 1][currenty] == 0) {
								pointInXY++;
								x[pointInXY] = currentx + 1;
								y[pointInXY] = currenty;
							}

							if (list[currentx][currenty - 1] == 0 && templist[currentx][currenty - 1] == 0) {
								pointInXY++;
								x[pointInXY] = currentx;
								y[pointInXY] = currenty - 1;
							}

							if (list[currentx][currenty + 1] == 0 && templist[currentx][currenty + 1] == 0) {
								pointInXY++;
								x[pointInXY] = currentx;
								y[pointInXY] = currenty + 1;
							}
						}
						free++;
					}
				}
			}
		}

		int pointInAreas = 1;
		int biggest = 0;
		int posbiggest = 0;
		while (areas[pointInAreas] != 0) {
			if (areas[pointInAreas] > biggest) {
				biggest = areas[pointInAreas];
				posbiggest = pointInAreas;
			}
			pointInAreas++;
		}

		for (int i = 0; i < list.length; i++) {
			for (int j = 0; j < list[0].length; j++) {
				if (templist[i][j] != posbiggest) {
					list[i][j] = 1;
				}
			}
		}

	}

	public void onlyEges() {
		int[][] templist = new int[list.length][list[0].length];

		for (int i = 0; i < list.length; i++) {
			for (int j = 0; j < list[0].length; j++) {
				int n = 0;
				// System.out.println("i: " + i + ", j: " + j);
				for (int i2 = i - 1; i2 <= i + 1; i2++) {
					for (int j2 = j - 1; j2 <= j + 1; j2++) {

						if (!(i2 == i && j2 == j)) {
							// System.out.println("i2: " + i2 + ", j2: " + j2);
							if (i2 < 0 || i2 > list.length - 1 || j2 < 0 || j2 > list[0].length - 1) {
								n++;
							} else {
								if (list[i2][j2] == 1) {
									n++;
								}
							}
						}
					}
				}
				if (list[i][j] == 0) {
					templist[i][j] = 0;
				} else {
					if (n == 8) {
						templist[i][j] = 0;
					} else {
						templist[i][j] = 1;
					}
				}

			}
		}
		list = templist;

	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int[][] getList() {
		return list;
	}

	public void setList(int[][] list) {
		this.list = list;
	}

	public Random getRan() {
		return ran;
	}

	public void setRan(Random ran) {
		this.ran = ran;
	}

	public int getIt() {
		return it;
	}

	public void setIt(int it) {
		this.it = it;
	}

	public int getFs() {
		return fs;
	}

	public void setFs(int fs) {
		this.fs = fs;
	}

	public double getRandon() {
		return randon;
	}

	public void setRandon(double randon) {
		this.randon = randon;
	}

	public boolean isFillholes() {
		return fillholes;
	}

	public void setFillholes(boolean fillholes) {
		this.fillholes = fillholes;
	}
}