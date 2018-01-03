/*
 * Copyright (c) 2017 <C4>
 *
 * This Java class is distributed as a part of Consecration.
 * Consecration is open source and licensed under the GNU General Public License v3.
 * A copy of the license can be found here: https://www.gnu.org/licenses/gpl.txt
 */

package c4.consecration;

import c4.consecration.proxy.CommonProxy;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLFingerprintViolationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

@Mod(	modid = Consecration.MODID,
		name = Consecration.MODNAME,
		version = Consecration.MODVER,
		dependencies = "required-after:forge@[14.23.1.2555,)",
        acceptedMinecraftVersions = "[1.12.2, 1.13)",
        certificateFingerprint = "5d5b8aee896a4f5ea3f3114784742662a67ad32f")
public class Consecration {

    public static final String MODID = "consecration";
    public static final String MODNAME = "Consecration";
    public static final String MODVER = "0.0.3";

    @SidedProxy(clientSide = "c4.consecration.proxy.ClientProxy", serverSide = "c4.consecration.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Mod.Instance
    public static Consecration instance;

    public static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent evt) {
        logger = evt.getModLog();
        proxy.preInit(evt);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent evt) {
        proxy.init(evt);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent evt) {
        proxy.postInit(evt);
    }

    @Mod.EventHandler
    public void onFingerPrintViolation(FMLFingerprintViolationEvent evt) {
        FMLLog.log.log(Level.ERROR, "Invalid fingerprint detected! The file " + evt.getSource().getName() + " may have been tampered with. This version will NOT be supported by the author!");
    }
}
