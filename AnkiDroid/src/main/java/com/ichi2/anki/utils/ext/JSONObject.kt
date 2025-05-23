/*
 * Copyright (c) 2024 Ashish Yadav <mailtoashish693@gmail.com>
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.ichi2.anki.utils.ext

import org.json.JSONObject

/**
 * @return `null` if:
 * * The key does not exist
 * * The value is [null][JSONObject.NULL]
 * * ⚠️ JVM only. The value is not a string (`{ }` etc...)
 * Otherwise, returns the value mapped by [key]. In Android, that means the potentially non-string
 * value is converted to string first.
 */
fun JSONObject.getStringOrNull(key: String): String? {
    if (!has(key)) return null
    if (isNull(key)) return null
    return try {
        // Note that [JSONObject]'s [getString] behavior differs between JVM and Android.
        // In Android, the value is converted to string before being returned.
        // In the JVM, a non string value is ignored and null is returned.
        getString(key)
    } catch (_: Exception) {
        null
    }
}
