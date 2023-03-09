package org.example;

import java.util.Properties;

public class MessageSupportFactory {
    private static MessageSupportFactory instance;
    private Properties props;
    private MessageProvider provider;
    private MessageRenderer renderer;

    private MessageSupportFactory() {
        props = new Properties();
        try {
            /*String workingDir = System.getProperty("user.dir");
            File propsFile = new File(workingDir, "/src/main/resources/msf.properties");
            String propsPath = propsFile.getAbsolutePath();
            System.out.println(propsPath);*/
            props.load(this.getClass().getResourceAsStream("/msf.properties"));
            String rendererClass = props.getProperty("renderer.class");
            String providerClass = props.getProperty("provider.class");

            renderer = (MessageRenderer) Class.forName(rendererClass).getConstructor().newInstance();
            provider = (MessageProvider) Class.forName(providerClass).getConstructor().newInstance();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    static {
        instance = new MessageSupportFactory();
    }

    public static MessageSupportFactory getInstance() {
        return instance;
    }

    public MessageRenderer getMessageRenderer() {
        return this.renderer;
    }

    public MessageProvider getMessageProvider() {
        return this.provider;
    }

}

