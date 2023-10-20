package dev.lupluv.simplegamemode.utils

import dev.lupluv.simplegamemode.Main
import kotlin.reflect.KClass


fun getItzLogger(clazz: KClass<*>): Logger = Logger(clazz)

class Logger(private val clazz: KClass<*>) {
    fun info(info: String) {
        Main.instance.sendConsoleMessage("§a[${clazz.simpleName}] INFO: §7$info")
    }
    fun warn(warn: String) {
        Main.instance.sendConsoleMessage("§e[${clazz.simpleName}] WARN: §7$warn")
    }
    fun error(error: String) {
        Main.instance.sendConsoleMessage("§c[${clazz.simpleName}] ERROR: §7$error")
    }

    fun info(info: Array<String>, placeholders: Map<String, String> = mapOf()) {
        Main.instance.server.consoleSender.sendCMessage(info.map { "§a[${clazz.simpleName}] INFO: §7$it" }.toTypedArray(), placeholders)
    }
    fun warn(warn: Array<String>, placeholders: Map<String, String> = mapOf()) {
        Main.instance.server.consoleSender.sendCMessage(warn.map { "§e[${clazz.simpleName}] WARN: §7$it" }.toTypedArray(), placeholders)
    }
    fun error(error: Array<String>, placeholders: Map<String, String> = mapOf()) {
        Main.instance.server.consoleSender.sendCMessage(error.map { "§c[${clazz.simpleName}] ERROR: §7$it" }.toTypedArray(), placeholders)
    }
}