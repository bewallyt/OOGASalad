package engine;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.io.*;

import javax.imageio.ImageIO;

public class AddTransparancy
{
  AddTransparancy() throws IOException
  {
    String imagePath = "C:/Users/Lee Weisberger/Pictures";
    File inFile = new File(imagePath, "p.png");
    BufferedImage image = ImageIO.read(inFile);

    Image transpImg1 = TransformGrayToTransparency(image);
    BufferedImage resultImage1 = ImageToBufferedImage(transpImg1, image.getWidth(), image.getHeight());

    File outFile1 = new File(imagePath, "map_with_transparency1.png");
    ImageIO.write(resultImage1, "PNG", outFile1);

    Image transpImg2 = TransformColorToTransparency(image, new Color(255, 255, 255), new Color(255, 255, 255));
    BufferedImage resultImage2 = ImageToBufferedImage(transpImg2, image.getWidth(), image.getHeight());

    File outFile2 = new File(imagePath, "map_with_transparency2.png");
    ImageIO.write(resultImage2, "PNG", outFile2);
  }

  private Image TransformGrayToTransparency(BufferedImage image)
  {
    ImageFilter filter = new RGBImageFilter()
    {
      public final int filterRGB(int x, int y, int rgb)
      {
        return (rgb << 8) & 0xFF000000;
      }
    };

    ImageProducer ip = new FilteredImageSource(image.getSource(), filter);
      return Toolkit.getDefaultToolkit().createImage(ip);
  }

  private Image TransformColorToTransparency(BufferedImage image, Color c1, Color c2)
  {
    // Primitive test, just an example
    final int r1 = c1.getRed();
    final int g1 = c1.getGreen();
    final int b1 = c1.getBlue();
    final int r2 = c2.getRed();
    final int g2 = c2.getGreen();
    final int b2 = c2.getBlue();
    ImageFilter filter = new RGBImageFilter()
    {
      public final int filterRGB(int x, int y, int rgb)
      {
        int r = (rgb & 0xFF0000) >> 16;
        int g = (rgb & 0xFF00) >> 8;
        int b = rgb & 0xFF;
        if (r >= r1 && r <= r2 &&
            g >= g1 && g <= g2 &&
            b >= b1 && b <= b2)
        {
          // Set fully transparent but keep color
          return rgb & 0xFFFFFF;
        }
        return rgb;
      }
    };

    ImageProducer ip = new FilteredImageSource(image.getSource(), filter);
      return Toolkit.getDefaultToolkit().createImage(ip);
  }

  private BufferedImage ImageToBufferedImage(Image image, int width, int height)
  {
    BufferedImage dest = new BufferedImage(
        width, height, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2 = dest.createGraphics();
    g2.drawImage(image, 0, 0, null);
    g2.dispose();
    return dest;
  }

  public static void main(String[] args) throws IOException
  {
    AddTransparancy at = new AddTransparancy();
  }
}
