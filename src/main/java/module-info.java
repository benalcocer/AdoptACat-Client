module AdoptACat.Client {
    requires jcommander;
    requires java.logging;
    requires org.apache.httpcomponents.httpclient;
    requires org.apache.httpcomponents.httpcore;
    requires org.apache.commons.io;
    requires org.json;

    opens arguments;
}