package sample01;

import java.io.IOException;
import java.net.URL;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import sample01.BlogPoster.PostType;

public class PostExample {

	public static void main(String[] args) throws Exception {
		// the url of your xmlrpc.php, typically
		// of the form http://your.domain.here/wordpress/xmlrpc.php
		String xmlRpcUrl = "http://kta.wp.xdomain.jp/xmlrpc.php";
		// this key is not used in my wordpress version
		String apiKey = "xx";
		String userName = "kta";
		String password = "jazzyk402";
		// in my wordpress version the blogId is "1"
		String blogId = "1";

		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
		config.setServerURL(new URL(xmlRpcUrl));

		XmlRpcClient client = new XmlRpcClient();
		client.setConfig(config);

		BlogInfo blogInfo = new BlogInfo(apiKey, userName, password, blogId);

		BlogPoster poster = new BlogPoster(client, blogInfo);
		poster.setPostType(PostType.publish);
		poster.post(contents());
	}

	private static String contents() throws IOException {
		// According to the wordpress post format the title and
		// category id of the post get some special mark up.
		return "<title>Look how this wordpress post got created from java!</title>" + "<category>6</category>";
	}
}