package com.tealcube.minecraft.bukkit.mythicdrops.api.enchantments

import org.apache.commons.lang3.math.NumberUtils
import org.bukkit.enchantments.Enchantment

/**
 * A class containing an [Enchantment] and a minimum and maximum level.
 */
class MythicEnchantment(val enchantment: Enchantment?, minimumLevel: Int, maximumLevel: Int) {
    private val minimumLevel: Int = Math.max(1, Math.min(minimumLevel, maximumLevel))
    private val maximumLevel: Int = Math.min(Math.max(minimumLevel, maximumLevel), 127)

    /**
     * Returns the range between the minimum and maximum levels of the Enchantment. <br></br> Equivalent of `{
     * #getMaximumLevel()} - { #getMinimumLevel()} `
     *
     * @return range between the minimum and maximum levels
     */
    val range: Double
        get() = (getMaximumLevel() - getMinimumLevel()).toDouble()

    /**
     * Returns the maximum level of the Enchantment.
     *
     * @return maximum level
     */
    fun getMaximumLevel(): Int {
        return Math.min(maximumLevel, 127)
    }

    /**
     * Returns the minimum level of the Enchantment.
     *
     * @return minimum level
     */
    fun getMinimumLevel(): Int {
        return Math.max(minimumLevel, 1)
    }

    companion object {
        @JvmStatic
        fun fromString(string: String): MythicEnchantment? {
            var ench: Enchantment? = null
            var value1 = 0
            var value2 = 0
            val split = string.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            when (split.size) {
                0 -> {
                }
                1 -> {
                }
                2 -> {
                    ench = Enchantment.getByName(split[0])
                    if (ench != null) {
                        value2 = NumberUtils.toInt(split[1], 1)
                        value1 = value2
                    }
                }
                else -> {
                    ench = Enchantment.getByName(split[0])
                    if (ench == null) {
                        value1 = NumberUtils.toInt(split[1], 1)
                        value2 = NumberUtils.toInt(split[2], 1)
                    }
                }
            }
            return if (ench == null) {
                null
            } else MythicEnchantment(ench, value1, value2)
        }
    }
}