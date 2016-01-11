package sample01;

public class BlogInfo {
	private String apiKey;
	private String userName;
	private String password;
	private String blogId;

	public BlogInfo(String apiKey, String userName, String password, String blogId) {
		this.apiKey = apiKey;
		this.userName = userName;
		this.password = password;
		this.blogId = blogId;
	}

	public String getApiKey() {
		return apiKey;
	}

	public String getBlogId() {
		return blogId;
	}

	public String getPassword() {
		return password;
	}

	public String getUserName() {
		return userName;
	}
}