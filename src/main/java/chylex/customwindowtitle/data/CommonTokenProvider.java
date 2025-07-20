package chylex.customwindowtitle.data;

import net.minecraft.SharedConstants;
import net.minecraft.client.Minecraft;

public interface CommonTokenProvider {
	default String getMinecraftVersion() {
		return SharedConstants.getCurrentVersion().name();
	}
	
	String getModVersion(String modId);
	
	default String getUsername() {
		return Minecraft.getInstance().getUser().getName();
	}
}
