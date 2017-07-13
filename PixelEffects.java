/* A class to implement the various pixel effects.
 * @author kenneth2
 */
public class PixelEffects {
	static int width;
	static int height;
	static int[][] result = new int[width][height];

//	public static void readImageMetrics(int[][] source) {
//		width = source.length;
//		height = source[0].length;
//		result = new int[width][height];
//	}



	/** Copies the source image to a new 2D integer image */
	public static int[][] copy(int[][] source) {
		width = source.length;
		height = source[0].length;
		result = new int[width][height];

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int rgb = source[i][j];
				int red = RGBUtilities.toRed(rgb);
				int green = RGBUtilities.toGreen(rgb);
				int blue = RGBUtilities.toBlue(rgb);
				result[i][j] = RGBUtilities.toRGB(red, green, blue);
			}
		}

		return result;
	}

	/** Removes "redeye" caused by a camera flash. sourceB is not used */
	public static int[][] redeye(int[][] source) {
		width = source.length;
		height = source[0].length;
		result = new int[width][height];

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int rgb = source[i][j];
				int red = RGBUtilities.toRed(rgb);
				int green = RGBUtilities.toGreen(rgb);
				int blue = RGBUtilities.toBlue(rgb);
				if (red > 4 * Math.max(green, blue) && red > 64)
					red = green = blue = 0;
				result[i][j] = RGBUtilities.toRGB(red, green, blue);
			}
		}

		return result;
	}

	/**
	 * Resize the array image to the new width and height
	 * You are going to need to figure out how to map between a pixel
	 * in the destination image to a pixel in the source image
	 * @param source
	 * @param newWidth
	 * @param newHeight
	 * @return
	 */
	public static int[][] resize(int[][] source, int newWidth, int newHeight) {
//		readImageMetrics(source);

		width = source.length;
		height = source[0].length;
		result = new int[newWidth][newHeight];

		for (int i = 0; i < newWidth; i++) {
			for (int j = 0; j < newHeight; j++) {
//				int rgb = source[i][j];
//				int red = RGBUtilities.toRed(rgb);
//				int green = RGBUtilities.toGreen(rgb);
//				int blue = RGBUtilities.toBlue(rgb);
				result[i][j] = source[(i*width) / newWidth][((j*height) / newHeight)];
			}
		}
		// Hints: Use two nested for loops between 0... newWidth-1 and 0.. newHeight-1 inclusive.
		// Hint: You can just use relative proportion to calculate the x (or y coordinate)  in the original image.
		// For example if you're setting a pixel halfway across the image, you should be reading half way across the original image too.
		return result;
	}

	/**
	 * Create a new image array that is the same dimesions of the reference
	 * array. The array may be larger or smaller than the source. Hint -
	 * this methods should be just one line - delegate the work to resize()!
	 *
	 * @param source
	 *            the source image
	 * @param reference
	 * @return the resized image
	 */
//		turns source to reference size
	public static int[][] resize(int[][] source, int[][] reference) {

		width = source.length;
		height = source[0].length;
		int newWidth = reference.length;
		int newHeight = reference[0].length;
		result = new int[newWidth][newHeight];

		for (int i = 0; i < newWidth; i++) {
			for (int j = 0; j < newHeight; j++) {
				int rgb = source[(i*width) / newWidth][(j*height) / newHeight];
				int red = RGBUtilities.toRed(rgb);
				int green = RGBUtilities.toGreen(rgb);
				int blue = RGBUtilities.toBlue(rgb);
				result[i][j] = RGBUtilities.toRGB(red, green, blue);
			}
		}

		return result;
	}

	/**
	 * Half the size of the image. This method should be just one line! Just
	 * delegate the work to resize()!
	 */
	public static int[][] half(int[][] source) {
		width = source.length;
		height = source[0].length;

		return resize(source, width / 2, height / 2);
	}

	/** Flip the image vertically */
	public static int[][] flip(int[][] source) {
		width = source.length;
		height = source[0].length;
		result = new int[width][height];

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int rgb = source[i][j];
				int red = RGBUtilities.toRed(rgb);
				int green = RGBUtilities.toGreen(rgb);
				int blue = RGBUtilities.toBlue(rgb);
				result[i][height - j -1] = RGBUtilities.toRGB(red, green, blue);
			}
		}

		return result;
	}

	/** Reverse the image horizontally */
	public static int[][] mirror(int[][] source) {width = source.length;
		height = source[0].length;
		result = new int[width][height];

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int rgb = source[i][j];
				int red = RGBUtilities.toRed(rgb);
				int green = RGBUtilities.toGreen(rgb);
				int blue = RGBUtilities.toBlue(rgb);
				result[width - i - 1][j] = RGBUtilities.toRGB(red, green, blue);
			}
		}

		return result;
	}

	/** Rotate the image */
	public static int[][] rotateLeft(int[][] source) {
		width = source.length;
		height = source[0].length;
		result = new int[height][width];

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int rgb = source[i][j];
				int red = RGBUtilities.toRed(rgb);
				int green = RGBUtilities.toGreen(rgb);
				int blue = RGBUtilities.toBlue(rgb);
				result[j][i] = RGBUtilities.toRGB(red, green, blue);
			}
		}

		flip(result);

		return result;
	}

	/** Merge the red,blue,green components from two images */
	public static int[][] merge(int[][] sourceA, int[][] sourceB) {
		// The output should be the same size as the input. Scale (x,y) values
		// when reading the background
		// (e.g. so the far right pixel of the source is merged with the
		// far-right pixel of the background).

		int[][] background = resize(sourceB, sourceA);

		width = sourceA.length;
		height = sourceA[0].length;
		result = new int[width][height];

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int rgbA = sourceA[i][j];
				int rgbB = background[i][j];

				int redA = RGBUtilities.toRed(rgbA);
				int greenA = RGBUtilities.toGreen(rgbA);
				int blueA = RGBUtilities.toBlue(rgbA);
				int redB = RGBUtilities.toRed(rgbB);
				int greenB = RGBUtilities.toGreen(rgbB);
				int blueB = RGBUtilities.toBlue(rgbB);
				int red = (redA + redB) / 2;
				int green = (greenA + greenB) / 2;
				int blue = (blueA + blueB) / 2;
				int average = RGBUtilities.toRGB(red, green, blue);
				result[i][j] = average;
			}
		}

		return result;
	}

	/**
	 * Replace the green areas of the foreground image with parts of the back
	 * image
	 */
	public static int[][] chromaKey(int[][] sourceA, int[][] sourceB) {
		if (sourceA.length != sourceB.length || sourceA[0].length != sourceB[0].length) {
			sourceA = resize(sourceA, sourceB);
		}

		width = sourceA.length;
		height = sourceA[0].length;
		result = new int[width][height];

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int rgbA = sourceA[i][j];
				int rgbB = sourceB[i][j];

				int redA = RGBUtilities.toRed(rgbA);
				int greenA = RGBUtilities.toGreen(rgbA);
				int blueA = RGBUtilities.toBlue(rgbA);
				int redB = RGBUtilities.toRed(rgbB);
				int greenB = RGBUtilities.toGreen(rgbB);
				int blueB = RGBUtilities.toBlue(rgbB);

				if (greenA > 75) {
					result[i][j] = RGBUtilities.toRGB(redB, greenB, blueB);
				} else result[i][j] = RGBUtilities.toRGB(redA, greenA, blueA);
			}
		}

		return result;
	}

	//grayscale
	public static int[][] funky(int[][] source, int[][] sourceB) {
		width = source.length;
		height = source[0].length;
		result = new int[width][height];

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int rgb = source[i][j];
				int red = RGBUtilities.toRed(rgb);
				int green = RGBUtilities.toGreen(rgb);
				int blue = RGBUtilities.toBlue(rgb);
				//creates balanced greyscale
				double redGreyscale = red * .21;
				double greenGreyscale = green * .72;
				double blueGreyscale = blue * .07;
				result[i][j] = RGBUtilities.toRGB((int)redGreyscale, (int)greenGreyscale, (int)blueGreyscale);
			}
		}

		return result;
	}
}
