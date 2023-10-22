# Cape Changer
This mod allows you to put on or change your cape. This mod is completely client sided and has taken me hours  to make. Hope you enjoy.  
## Features
- Free cape.
- Gui which allows you to toggle your custom cape on and off.
- Custom cape textures through resource pack.
## How to use
The default key to open the CapeChanger gui is 'R'. You can customise this in Key Binds.  
To customise the cape texture you have to go through a few steps. 
> Note, this is not an official resource pack tutorial.     
 
First, create a folder in `%appdata%/.minecraft/resourcepacks`.  
Now create a `pack.mcmeta` file inside the new folder.
<details>
<summary>pack.mcmeta content</summary>

```
{
  "pack": {
    "pack_format": PACK_FORMAT_FOR_MINECRAFT_VERSION,
    "description": "Gives you custom cape (requires https://modrinth.com/mod/capechanger)"
  }
}
```
You can get the pack_format version from the [Minecraft wiki page](https://minecraft.wiki/w/Pack_format)
</details>
  
Create a folder named assets.  
Now, create a folder inside assets named capechanger.  
Put your cape texture inside this folder and rename it to `cape.png`.  
You can download capes from [laby](https://laby.net/capes) and multiple different places.  
You can also make your own cape at [Skin MC's Cape editor](https://skinmc.net/capes/editor) 
  
Your resource pack should look like this:  
  
<details>
<summary>Folder</summary>

<details>
<summary>assets</summary>

<details>
<summary>capechanger</summary>

`cape.png`

</details>

</details>
  
`pack.mcmeta`

</details>
