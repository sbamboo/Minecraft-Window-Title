package chylex.customwindowtitle;

import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.Minecraft;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.sdl.SDLError;
import org.lwjgl.sdl.SDLPixels;
import org.lwjgl.sdl.SDLSurface;
import org.lwjgl.sdl.SDLVideo;
import org.lwjgl.sdl.SDL_Surface;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public final class IconChanger {
	private static final Logger LOGGER = LogManager.getLogger("CustomWindowTitle");
	
	private IconChanger() {}
	
	public static void setIcon(Path iconPath) {
		long windowHandle = Minecraft.getInstance().getWindow().handle();
		
		try (InputStream in = Files.newInputStream(iconPath); NativeImage image = NativeImage.read(in)) {
			SDL_Surface surface = SDLSurface.SDL_CreateSurfaceFrom(
				image.getWidth(),
				image.getHeight(),
				SDLPixels.SDL_PIXELFORMAT_ABGR8888,
				image.getPixelBytes(),
				image.getWidth() * 4
			);
			
			if (surface == null) {
				LOGGER.error("Failed to create window icon surface from path: {} - {}", iconPath, SDLError.SDL_GetError());
				return;
			}
			
			try {
				if (!SDLVideo.SDL_SetWindowIcon(windowHandle, surface)) {
					LOGGER.error("Failed to set window icon from path: {} - {}", iconPath, SDLError.SDL_GetError());
				}
			} finally {
				SDLSurface.SDL_DestroySurface(surface);
			}
		} catch (Exception e) {
			LOGGER.error("Failed to set window icon from path: {}", iconPath, e);
		}
	}
}
