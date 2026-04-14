import java.time.OffsetDateTime
import java.util.Date
import kotlin.time.Instant

plugins {
    id("dev.kikugie.stonecutter")
    id("net.fabricmc.fabric-loom-remap") version "1.15-SNAPSHOT" apply false
    // id("me.modmuss50.mod-publish-plugin") version "1.0.+" apply false
}

stonecutter active "1.19.4"

/*
// Make newer versions be published last
stonecutter tasks {
    order("publishModrinth")
    order("publishCurseforge")
}
 */

// See https://stonecutter.kikugie.dev/wiki/config/params
stonecutter parameters {
    val nowTime = OffsetDateTime.now();
    swaps["mod_version"] = "\"${property("mod.version")}\";"
    swaps["mod_version_name"] = "\"${(property("mod.version") as String).replaceFirst("-canary", " Canary Build").replaceFirst("-beta-", " Beta Snapshot ").replaceFirst("-pre-", " Pre-Release ").replaceFirst("-rc-", " Release Candidate ")}" +
            "${(if ((property("mod.version") as String).contains("-canary")) String.format(" %d%02d", nowTime.month.value, nowTime.dayOfMonth) else "")}\";"
    swaps["minecraft"] = "\"${node.metadata.version}\";"
    constants["release"] = property("mod.id") != "template"
    dependencies["fapi"] = node.project.property("deps.fabric_api") as String

    replacements {
    }
}
