package cn.zbx1425.mtrsteamloco;

import java.math.BigDecimal;

public interface UFEInfo {
    /**
     * Mod id for the mod
     */
    String MOD_ID = "mtrsteamloco";

    /**
     * Mod name for the mod
     */
    String MOD_NAME = "Trackstage";

    /**
     * Mod version for the mod
     * <br/>
     * role (Y: Year, X: Major, Z: Minor, x: Build frequency):
     * <ul>
     *     <li>Action Build: YY[.XX[.ZZ]]-canary</li>
     *     <li>Normal Preview: YY[.XX[.ZZ]]-beta-x</li>
     *     <li>Pre-Release: YY[.XX[.ZZ]]-pre-x</li>
     *     <li>Release Candidate: YY[.XX[.ZZ]]-rc-x</li>
     *     <li>Normal Release: YY[.XX[.ZZ]]</li>
     * </ul>
     */
    String VERSION = /*$ mod_version*/"26-canary";

    /**
     * Mod version for the mod
     * <br/>
     * role (Y: Year, X: Major, Z: Minor, M: Month, D: Day, x: Build frequency):
     * <ul>
     *     <li>Action Build: YY[.XX[.ZZ]] Canary Build MMDD</li>
     *     <li>Normal Preview: YY[.XX[.ZZ]] Bata Snapshot x</li>
     *     <li>Pre-Release: YY[.XX[.ZZ]] Pre-Release x</li>
     *     <li>Release Candidate: YY[.XX[.ZZ]] Release Candidate x</li>
     *     <li>Normal Release: YY[.XX[.ZZ]]</li>
     * </ul>
     */
    String VERSION_NAME = /*$ mod_version_name*/"26 Canary Build 414";

    /**
     * Service version for the mod
     */
    int SERVICE_VERSION = 1;
}
