package org.computerspecsviewer.infoquery.display;

import oshi.util.EdidUtil;
import oshi.util.ParseUtil;

public class DisplayDescriptors {
    public String descriptorsContent;

    public DisplayDescriptors(byte[] edid) {
        StringBuilder descContentBuilder = new StringBuilder();
        byte[][] descriptors = EdidUtil.getDescriptors(edid);

        // Reference: https://github.com/oshi/oshi/blob/master/oshi-core/src/main/java/oshi/util/EdidUtil.java#L256
        for(byte[] descriptor: descriptors) {
            switch(EdidUtil.getDescriptorType(descriptor)) {
                case 0xff:
                    descContentBuilder.append("Serial Number: ").append(EdidUtil.getDescriptorText(descriptor)).append("\n");
                    break;
                case 0xfe:
                    break;
                case 0xfd:
                    descContentBuilder.append("Range Limits: ").append(EdidUtil.getDescriptorRangeLimits(descriptor)).append("\n");
                    break;
                case 0xfc:
                    descContentBuilder.append("Monitor Name: ").append(EdidUtil.getDescriptorText(descriptor)).append("\n");
                    break;
                case 0xfb:
                    descContentBuilder.append("White Point Data: ").append(ParseUtil.byteArrayToHexString(descriptor)).append("\n");
                    break;
                case 0xfa:
                    descContentBuilder.append("Standard Timing ID: ").append(ParseUtil.byteArrayToHexString(descriptor)).append("\n");
                    break;
                default:
                    if (EdidUtil.getDescriptorType(descriptor) <= 0x0f && EdidUtil.getDescriptorType(descriptor) >= 0x00) {
                        descContentBuilder.append("Manufacturer Data: ").append(ParseUtil.byteArrayToHexString(descriptor)).append("\n");
                    } else {
                        descContentBuilder.append("Preferred Timing: ").append(EdidUtil.getTimingDescriptor(descriptor)).append("\n");
                    }
                    break;
            }
        }

        descriptorsContent = descContentBuilder.toString();
    }

    @Override
    public String toString() {
        return descriptorsContent;
    }
}
