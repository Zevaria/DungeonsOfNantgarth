#version 330

layout (location = 0) in vec4 in_pos;

uniform mat4 projection;

void main()
{
	gl_Position = projection * in_pos;
}